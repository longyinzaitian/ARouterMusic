package video.cn.base.utils;

/**
 * @author husyin
 * @date 2019年3月10日
 */

public class RouteUtils {
    public static final String HOME_FRAGMENT_MAIN = "/home/main";
    public static final String CHAT_FRAGMENT_MAIN = "/chat/main";
    public static final String RECOM_FRAGMENT_MAIN = "/recom/main";
    public static final String ME_FRAGMENT_MAIN = "/me/main";
    public static final String ME_LOGIN = "/me/main/login";
    /**跳转到eventBus数据接收页面*/
    public static final String ME_EVENT_BUS = "/me/main/EventBus";
    /**跳转到TextOne数据接收页面*/
    public static final String ME_TEXT_ONE = "/me/main/TextOne";
     /**跳转到Test公用页面*/
    public static final String ME_TEST = "/me/main/Test";
     /**路由拦截*/
    public static final String ME_TEST_2 = "/me/main/Test2";
     /**跳转到web view页面*/
    public static final String ME_WEB_VIEW = "/me/main/WebView";

     /**跳转到依赖注入页面*/
    public static final String ME_INJECT = "/me/main/Inject";
    /**
     * 依赖注入使用，注意：必须实现SerializationService进行注册，
     */
    public static final String HOME_JSON_SERVICE = "/huangxiaoguo/json";

    public static final String CHAT_FOR_RESULT = "/chat/main/ForResult";
    /**模块间服务调用，调用chat模块的接口*/
    public static final String SERVICE_CHAT = "/chat/service";
    /**路由拦截*/
    public static final String CHAT_INTERCEPTOR = "/chat/main/Interceptor";

    /**
     * 专门的分组，这里就叫做needLogin组，凡是在这个组下的，都会进行登录操作
     */
    public static final String NEED_LOGIN_TEST_3 = "/needLogin/main/Test3";

}
