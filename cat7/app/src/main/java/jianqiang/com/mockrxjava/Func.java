package jianqiang.com.mockrxjava;

//T对应于参数类型而R对应于返回类型
public interface Func<T, R> {
    R call(T t);
}