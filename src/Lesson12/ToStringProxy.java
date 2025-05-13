package Lesson12;

import java.lang.reflect.*;

public class ToStringProxy {

    /**
     * Retorna um proxy de `iface` cujo método toString() é substituído
     * por ObjectDumper.dump(target), e todos os outros métodos
     * são delegados diretamente ao objeto-alvo.
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> iface, T target) {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // intercepta toString sem parâmetros
                if (method.getName().equals("toString") && method.getParameterCount() == 0) {
                    return ObjectDumper.dump(target);
                }
                // delega tudo o mais
                return method.invoke(target, args);
            }
        };
        return (T) Proxy.newProxyInstance(
                iface.getClassLoader(),
                new Class<?>[]{ iface },
                handler
        );
    }
}
