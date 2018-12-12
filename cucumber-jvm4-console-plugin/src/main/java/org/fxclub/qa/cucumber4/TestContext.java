package org.fxclub.qa.cucumber4;

import java.util.HashMap;

/**
 * Created by majer-dy on 19/04/2017.
 */
public class TestContext {

    private static final ThreadLocal<TestContext> CONTEXT = new ThreadLocal<>();

    private String id;
    private HashMap<Class,Object> helpers = new HashMap<>();
    private HashMap<String,Object> data = new HashMap<>();

    public TestContext(){}

    public TestContext(String id){
        this.id = id;
    }

    public static void setContext(TestContext context){
        CONTEXT.set(context);
    }

    public static TestContext getContext(){
        if(CONTEXT.get() == null){
            setContext(new TestContext());
        }
        return CONTEXT.get();
    }

    public static void removeHelper(){
        CONTEXT.remove();
    }

    public static void set(Object helper){
        getContext().helpers.put(helper.getClass(), helper);
    }

    public static <T>T get(Class<T> helperClass){
        Object helper = getContext().helpers.get(helperClass);
        return (T) helper;
    }

    public static void set(String key, Object object){
        getContext().data.put(key, object);
    }

    @SuppressWarnings("unchecked")

    public static <T>T get(String key){
        return (T) getContext().data.get(key);
    }

    public static <T>T get(String key, Class<T> type){
        return (T) get(key);
    }
}
