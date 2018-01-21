package com.bonson.resource.dialog;

import android.databinding.ObservableArrayList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bonson.resource.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangjiancheng on 18/1/14.
 */

public class ActionSheetDialog extends DialogFragment {
    private TextView tvTitle;
    private ListView lvContent;

    private TextView tvCancel;
    private View topLine;
    private String title = "";

    private List<ActionSheet> actionSheets = new ArrayList<>();
    private ActionSheetAdapter actionSheetAdapter;
    private boolean outside = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // 去掉标题 死恶心死恶心的
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.action_sheet_dialog, container, false);
        tvTitle = view.findViewById(R.id.tv_title);
        lvContent = view.findViewById(R.id.lv_content);
        tvCancel = view.findViewById(R.id.tv_cancel);
        topLine = view.findViewById(R.id.top_line);
        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                    dismiss();
                }
            }
        });
        actionSheetAdapter = new ActionSheetAdapter(actionSheets);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 设置背景透明
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes(attributes);
        getDialog().setCanceledOnTouchOutside(outside);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle.setText(title);
        lvContent.setAdapter(actionSheetAdapter);
        if (actionSheetAdapter.getCount() == 0) {
            topLine.setVisibility(View.GONE);
        }
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (clickListener != null) {
                    clickListener.onClick(v);
                }
            }
        });
    }

    public ActionSheetDialog setCanceledOnTouchOutside(boolean outside) {
        this.outside = outside;
        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(outside);
        }
        return this;
    }

    private View.OnClickListener clickListener;

    public ActionSheetDialog setCancelListener(View.OnClickListener cancelListener) {
        this.clickListener = cancelListener;
        return this;
    }

    public ActionSheetDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public ActionSheetDialog setActionSheet(String[] content) {
        actionSheets.clear();
        for (String text : content) {
            actionSheets.add(new ActionSheet(text));
        }
        return this;
    }

    public ActionSheetDialog setActionSheet(String[] content, int color) {
        actionSheets.clear();
        for (String text : content) {
            actionSheets.add(new ActionSheet(text, color));
        }
        return this;
    }

    public ActionSheetDialog addActionSheet(String text, int color) {
        actionSheets.add(new ActionSheet(text, color));
        return this;
    }

    public ActionSheetDialog clear() {
        actionSheets.clear();
        return this;
    }

    class ActionSheet {
        String text;
        int color = Color.WHITE;

        public ActionSheet(String text) {
            this.text = text;
        }

        public ActionSheet(String text, int color) {
            this.text = text;
            this.color = color;
        }
    }

    private OnItemClickListener onItemClickListener;

    public ActionSheetDialog setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ActionSheetAdapter extends BaseAdapter {
        List<ActionSheet> actionSheets;

        public ActionSheetAdapter(List<ActionSheet> actionSheets) {
            this.actionSheets = actionSheets;
        }

        @Override
        public int getCount() {
            if (actionSheets == null) {
                return 0;
            }
            return actionSheets.size();
        }

        @Override
        public Object getItem(int position) {
            return actionSheets.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_action_sheet, parent, false);
            }
            TextView tvActionSheet = convertView.findViewById(R.id.tv_action);
            ActionSheet actionSheet = actionSheets.get(position);
            if (getCount() == 1) {
                tvActionSheet.setBackgroundResource(R.drawable.action_sheet_bottom_sel);
            } else {
                if (position != getCount() - 1) {
                    tvActionSheet.setBackgroundResource(R.drawable.action_sheet_middle_sel);
                } else {
                    tvActionSheet.setBackgroundResource(R.drawable.action_sheet_bottom_sel);
                }
            }
            tvActionSheet.setText(actionSheet.text);
            tvActionSheet.setTextColor(actionSheet.color);
            return convertView;
        }
    }
}
