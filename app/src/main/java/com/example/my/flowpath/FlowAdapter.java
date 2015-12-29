package com.example.my.flowpath;/**
 * Created by ttarfall on 2015/12/25.
 */

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author ttarfall
 * @date 2015-12-25 17:37
 */
public class FlowAdapter extends BaseAdapter{

    private Context context;
    private List<FlowPathEntity> entityList;
    protected LayoutInflater inflater;

    public FlowAdapter(Context context, List<FlowPathEntity> entityList) {
        this.context = context;
        this.entityList = entityList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return entityList==null?0:entityList.size();
    }

    @Override
    public Object getItem(int position) {
        if(entityList != null && entityList.size()>position)
            return  entityList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ItemHolder{
        private FlowPathView rnvNumber;
        private TextView tvText;
    }

    public Resources getResources() {
        return context.getResources();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = new ItemHolder();
            holder.rnvNumber = (FlowPathView)convertView.findViewById(R.id.rnv_number);
            holder.tvText = (TextView)convertView.findViewById(R.id.tv_text);
            convertView.setTag(holder);
        } else
            holder = (ItemHolder) convertView.getTag();

        FlowPathEntity entity = entityList.get(position);
        holder.rnvNumber.setNumber(entity.getNumber());
        holder.rnvNumber.setLineTopVisiable(entity.isTopLineVisiable());
        holder.rnvNumber.setLineBottomVisiable(entity.isBottomLineVisiable());
        if(entity.isCurrentNumber()){
            holder.rnvNumber.setRoundColor(getResources().getColor(R.color.textcolor_black));
        } else {
            holder.rnvNumber.setRoundColor(getResources().getColor(R.color.textcolor_light));
        }
        if(entity.isIconVisiable()){
            holder.rnvNumber.setRoundRadius((int)getResources().getDimension(R.dimen.dimen_10dp));
            holder.rnvNumber.setRoundColor(getResources().getColor(R.color.bg_green));
            holder.rnvNumber.setLineColor(getResources().getColor(R.color.bg_green));
            holder.rnvNumber.setImageResId(R.mipmap.icon_success);
        } else {
            holder.rnvNumber.setRoundRadius((int)getResources().getDimension(R.dimen.dimen_5dp));
            holder.rnvNumber.setRoundColor(getResources().getColor(R.color.textcolor_middle));
            holder.rnvNumber.setLineColor(getResources().getColor(R.color.textcolor_light));
            holder.rnvNumber.setImageResId(0);
        }
        holder.tvText.setText(entity.getText());
        return convertView;
    }
}
