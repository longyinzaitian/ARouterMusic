package video.cn.home.adapter.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.husy.network.model.Data;

import video.cn.home.R;

/**
 * @author husy
 * @date 2019/9/7
 */
public class HomeTextHeaderViewHolder extends RecyclerView.ViewHolder {

    private TextView textHeader;

    public HomeTextHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        textHeader = itemView.findViewById(R.id.adapter_layout_text_header);
    }

    public void setData(Data data) {
        if (data == null) {
            textHeader.setText("- Sep. 06, Brunch -");
            return;
        }
        textHeader.setText(data.text);
    }

}
