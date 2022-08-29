package mvparms.src.app_package

import com.android.tools.idea.wizard.template.extractLetters


fun armsActivityJava(
        packageName: String,
        pageName: String,
        activityPackageName: String,
        componentPackageName: String,
        contractPackageName: String,
        presenterPackageName: String,
        activityLayoutName: String
) = """
package ${activityPackageName};

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import ${packageName}.R;
import ${packageName}.base.BaseTitleActivity;
import ${componentPackageName}.Dagger${pageName}Component;
import ${contractPackageName}.${pageName}Contract;
import ${presenterPackageName}.${pageName}Presenter;

import butterknife.BindView;
import butterknife.OnClick;

public class ${pageName}Activity extends BaseTitleActivity<${pageName}Presenter> implements ${pageName}Contract.View {

    @BindView(R.id.xx)
    TextView recyclerView;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        Dagger${pageName}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.${activityLayoutName};
    }

    @Override
    public void initTitle(@Nullable Bundle savedInstanceState) {

    } 
    
    @Override
    public void doNext(@Nullable Bundle savedInstanceState) {

    }
    
    @Override
    public void onBarcode(String barcode) {
        super.onBarcode(barcode);
        
    }
    
    @Override
    @OnClick({R.id.xx})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        
    }

}
"""


fun armsActivityKt(
        packageName: String,
        pageName: String,
        activityPackageName: String,
        componentPackageName: String,
        contractPackageName: String,
        presenterPackageName: String,
        activityLayoutName: String,
        moudlePackageName: String
) = """
package $activityPackageName

import android.os.Bundle
import android.view.View
import com.jess.arms.di.component.AppComponent
import ${packageName}.R
import ${componentPackageName}.Dagger${pageName}Component
import ${moudlePackageName}.${pageName}Module
import ${contractPackageName}.${pageName}Contract
import ${presenterPackageName}.${pageName}Presenter
import kotlinx.android.synthetic.main.${activityLayoutName}.*

class ${pageName}Activity : BaseTitleActivity<${pageName}Presenter>() , ${pageName}Contract.View {

    override fun setupActivityComponent(appComponent:AppComponent) {
        Dagger${pageName}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .${extractLetters(pageName[0].toLowerCase().toString())}${pageName.substring(1, pageName.length)}Module(${pageName}Module(this))
                .build()
                .inject(this)
    }

    override fun getLayoutId():Int {
       return R.layout.${activityLayoutName}
    }

    override fun initTitle(savedInstanceState: Bundle?) {
     
    }
    
    override fun doNext(savedInstanceState: Bundle?) {

    }
    
     override fun onBarcode(barcode: String, isScan: Boolean): Boolean {
        if (!super.onBarcode(barcode, isScan)) return false
        

        return true
    }
    
     override fun onClick(v: View?) {
        super.onClick(v)
        when (v?.id) {
            R.id.x ->
                
        }
    }
    
}

"""