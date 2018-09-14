package com.hzdongcheng.aidldemo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.hzdongcheng.aidldemo.adapter.BoxAdapter;
import com.hzdongcheng.aidldemo.bean.BoxStatus;
import com.hzdongcheng.aidldemo.bean.ICallBack;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * com.hzdogncheng.drivers包下是东城驱动服务的AIDL接口，该包定义了所有和硬件交互的接口，必需要导入到项目里；
 * 本demo只使用了部分AIDL接口，其他接口请到drivers包下查看。
 */


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private TextView mScannerDataText;
    private RecyclerView recyclerView;
    private Button mChechBoxStatusButton;
    private Button mBindButton;
    private Button mScannerButton;
    private TextView mTipsText;
    private BoxAdapter adapter;
    private ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private List<BoxStatus> boxStatusList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (HAL.init(this)){
            Toast.makeText(this,"驱动服务连接成功",Toast.LENGTH_SHORT).show();
            mBindButton.setText("驱动服务已绑定");
        }else {
            Toast.makeText(this,"驱动服务连接失败",Toast.LENGTH_SHORT).show();
        }




    }

    public void initView(){
        mBindButton = findViewById(R.id.bind);
        mScannerButton = findViewById(R.id.scanner);
        mScannerDataText = findViewById(R.id.tv_scanner_data);
        mChechBoxStatusButton = findViewById(R.id.check_box_status);
        mTipsText = findViewById(R.id.tips);
        mTipsText.setOnClickListener(this);
        mChechBoxStatusButton.setOnClickListener(this);
        mScannerButton.setOnClickListener(this);
        mBindButton.setOnClickListener(this);
        //添加4个箱子
        for (int i = 0; i < 4 ; i++) {
            boxStatusList.add(new BoxStatus());
        }

        //recycleView
        adapter = new BoxAdapter(boxStatusList, new BoxAdapter.BoxOnClickListner() {
            @Override
            public void onClick(int i) {
                if (HAL.openBox(String.valueOf(i+1))) {
                    Toast.makeText(MainActivity.this, (i+1)+"箱门已打开", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "箱门打开失败，请查看错误日志", Toast.LENGTH_SHORT).show();
                }
            }
        });
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bind:
                ServiceProviderInstance.getInstance().bind(view.getContext());
                break;
            case R.id.check_box_status:
                detectBoxStatus();
                break;
            case R.id.scanner:
                setScanner();
                break;
            default:
                break;
        }



    }


    /**
     * 检测箱子状态
     */
    public void detectBoxStatus(){
        //本demo出于便捷考虑，检测箱子状态时，使用的HAL.getBoxStatus(箱子名称)是根据箱子名称获取状态。
        //HAL下还有一个重载的HAL.getBoxStatus()方法，是根据副柜获取的。
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <4 ; i++) {
                    try {
                        BoxStatus status = HAL.getBoxStatus(String.valueOf(i+1));
                        if (status != null) {
                            BoxStatus boxStatus = boxStatusList.get(i);
                            //箱内是否有物品
                            boxStatus.setGoodsStatus(status.getGoodsStatus());
                            //箱门开关状态
                            boxStatus.setOpenStatus(status.getOpenStatus());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                    } catch (Exception e) {
                        Log.e(TAG,e.getMessage());
                    }
                }
            }
        },1,2, TimeUnit.SECONDS);
    }

    /**
     * 连接扫描枪服务
     */
    public void setScanner(){
        try {
            ServiceProviderInstance.getInstance().getScannerController();
            //扫描枪数据回调
            HAL.setScannerCallBack(new ICallBack() {
                @Override
                public void onMessage(final String data, Integer type) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mScannerDataText.setText(data);
                        }
                    });

                    Log.d(TAG,data);
                }
            });
            HAL.toggleBarcode(true);
            HAL.toggleQRCode(true);

        } catch (Exception e) {
            Log.e(TAG,e.getMessage());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Unbind
        ServiceProviderInstance.getInstance().unBind(this);
    }

}
