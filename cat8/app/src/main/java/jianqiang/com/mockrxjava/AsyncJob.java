package jianqiang.com.mockrxjava;

public abstract class AsyncJob<T> {
    public abstract void start(Callback<T> callback);

    public <R> AsyncJob<R> map(Func<T, R> func){
        final AsyncJob<T> source = this;
        return new AsyncJob<R>() {
            @Override
            public void start(Callback<R> callback) {
                source.start(new Callback<T>() {
                    @Override
                    public void onSuccess(T result) {
                        R mapped = func.call(result);
                        callback.onSuccess(mapped);
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
    }

    public <R> AsyncJob<R> flatMap(Func<T, AsyncJob<R>> func){
        final AsyncJob<T> source = this;
        return new AsyncJob<R>() {
            @Override
            public void start(Callback<R> callback) {
                source.start(new Callback<T>() {

                    @Override
                    public void onSuccess(T result) {
                        AsyncJob<R> mapped = func.call(result);
                        mapped.start(new Callback<R>() {
                            @Override
                            public void onSuccess(R result) {
                                callback.onSuccess(result);
                            }

                            @Override
                            public void onError(Exception e) {
                                callback.onError(e);
                            }
                        });

                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
    }
}