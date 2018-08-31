package com.timper.res.base.loadmore;

import android.databinding.ObservableField;
import com.timper.res.R;
import com.timper.res.utils.context.App;

/**
 * User: tangpeng.yang
 * Date: 02/04/2018
 * Description:
 * FIXME
 */
public class LoadViewModel {

  public enum Status {//LOADMORE:加载中...    LOADCOMPLATE:加载完成     NOMORE:第一页已加载完所有数据
    LOADMORE, LOADCOMPLATE, NOMORE
  }

  public final ObservableField<String> more =
      new ObservableField<>(App.context().getResources().getString(R.string.app_toast_load_more));

  private Status status;

  public LoadViewModel() {
  }

  public LoadViewModel setStatus(Status status) {
    this.status = status;
    if (status == Status.LOADMORE) {
      more.set(App.context().getResources().getString(R.string.app_toast_load_more));
    } else if (status == Status.LOADCOMPLATE) {
      more.set(App.context().getResources().getString(R.string.app_toast_no_more_data));
    } else {
    }
    return this;
  }
}
