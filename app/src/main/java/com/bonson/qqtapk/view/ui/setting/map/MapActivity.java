package com.bonson.qqtapk.view.ui.setting.map;

import android.graphics.Color;
import android.os.Bundle;

import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.bonson.qqtapk.R;
import com.bonson.qqtapk.databinding.ActivityMapBinding;
import com.bonson.qqtapk.utils.binding.AdapterDataChangeFactory;
import com.bonson.resource.activity.BaseDaggerActivity;
import com.bonson.resource.dialog.ActionSheetDialog;
import com.bonson.resource.dialog.AlertDialog;

import javax.inject.Inject;

/**
 * Created by jiangjiancheng on 18/1/7.
 */

public class MapActivity extends BaseDaggerActivity<ActivityMapBinding> {
    @Inject
    MapViewModel viewModel;
    @Inject
    DownloadAdapter dowloadAdapter;
    @Inject
    CityAdapter cityAdapter;
    AlertDialog alertDialog;
    ActionSheetDialog sheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingLayout(R.layout.activity_map);
        binding.setViewModel(viewModel);
        setViewModel(viewModel);

        binding.toolbar.setTitle("离线地图");
        binding.toolbar.getTvLeft().setOnClickListener(v -> finish());
        binding.recCity.setAdapter(cityAdapter);
        binding.rgType.check(R.id.rb_city);
        DividerLine dividerLine = new DividerLine(this, viewModel.hostCount);
        binding.recCity.addItemDecoration(dividerLine);

        binding.rgType.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_city) {
                binding.recCity.setAdapter(cityAdapter);
                viewModel.all();
            } else {
                binding.recCity.setAdapter(dowloadAdapter);
                viewModel.download();
            }
        });

        cityAdapter.setBeans(viewModel.offlineSearchRecords);
        dowloadAdapter.setBeans(viewModel.updateElements);
        AdapterDataChangeFactory.create(cityAdapter).attach(viewModel.offlineSearchRecords);
        AdapterDataChangeFactory.create(dowloadAdapter).attach(viewModel.updateElements);
        cityAdapter.setClickListener(indexPath -> {
//            if (alertDialog == null) {
//                alertDialog = new AlertDialog();
//                alertDialog.setTitle("提示");
//                alertDialog.setContent("是否下载该地图?");
//                alertDialog.setSure("下载");
//                alertDialog.setCancel("取消");
//                alertDialog.setOnClickListener((view, witch) -> {
//                    alertDialog.dismiss();
//                    if (witch == AlertDialog.Sure) {
//                        viewModel.download(indexPath.row, indexPath.section);
//                    }
//                });
//
//            }
//            alertDialog.show(getSupportFragmentManager(), "download");
            viewModel.download(indexPath.row, indexPath.section);
        });
        dowloadAdapter.setOnItemClickListener(position -> {
            OfflineMap offlineMap = viewModel.updateElements.get(position);
            if (sheetDialog == null) {
                sheetDialog = new ActionSheetDialog();
                sheetDialog.setTitle("请选择操作选项");
                sheetDialog.setOnItemClickListener(v -> {
                    if (v == 0) {
                        if (!offlineMap.isDownload())
                            viewModel.update(1, position);
                        else {
                            viewModel.update(3, position);
                        }
                    } else if (v == 1) {
                        if (offlineMap.update) {
                            viewModel.update(2, position);
                        } else {
                            viewModel.update(3, position);
                        }
                    } else {
                        viewModel.update(3, position);
                    }
                });
            }
            sheetDialog.clear();

            switch (offlineMap.status) {
                case MKOLUpdateElement.DOWNLOADING:
                    sheetDialog.addActionSheet("暂停", Color.GRAY);
                    break;
                case MKOLUpdateElement.SUSPENDED:
                    sheetDialog.addActionSheet("开始", Color.RED);
                    break;
            }
            if (offlineMap.update) {
                sheetDialog.addActionSheet("更新", Color.BLUE);
            }
            sheetDialog.addActionSheet("删除", Color.RED);
            sheetDialog.show(getSupportFragmentManager(), "city");
        });
    }
}
