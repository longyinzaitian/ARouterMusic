package video.cn.base.base;

/**
 * @author husy
 * @date 2019/8/31
 */
public abstract class BasePresenter<T extends IView> {

    protected T iView;
    public BasePresenter(T iView) {
        this.iView = iView;
    }
}
