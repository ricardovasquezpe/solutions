package com.project.test.Core.Main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.test.Core.Main.presenter.MainPresenter;
import com.project.test.Core.Main.presenter.iMainPresenter;
import com.project.test.Core.MyApplication;
import com.project.test.Core.PriceList.view.PriceListActivity;
import com.project.test.Networking.models.response.MedicamentoModel;
import com.project.test.R;
import com.project.test.Util.Adapters.medicamentosListAdapter;
import com.project.test.Util.Dialogs.filter.view.filterDialog;
import com.project.test.Util.Dialogs.loadingDialog;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainView{

    private Unbinder unbinder;

    medicamentosListAdapter da_medicamentos;
    List<MedicamentoModel> medicamentosList;

    loadingDialog loading;
    filterDialog filter;

    iMainPresenter iMainPresenter;

    String ubigeo = "";

    @BindView(R.id.rv_medicamentos_act_main)
    RecyclerView rv_medicamentos;
    @BindView(R.id.et_search_med_act_main)
    EditText et_search_med;
    @BindView(R.id.tv_limpiar_act_main)
    TextView tv_limpiar;
    @BindView(R.id.sv_medicamentos_act_main)
    ScrollView sv_medicamentos;
    @BindView(R.id.rv_central_image_act_main)
    RelativeLayout rv_central_image;
    @BindView(R.id.iv_filter_act_main)
    ImageView iv_filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        unbinder = ButterKnife.bind(this);

        initVariables();
        initActions();

        rv_medicamentos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        da_medicamentos = new medicamentosListAdapter(medicamentosList, this);
        rv_medicamentos.setAdapter(da_medicamentos);
    }

    public void initVariables(){
        medicamentosList = new ArrayList<>();
        loading          = new loadingDialog(this);
        filter           = new filterDialog(this);
        filter.newInstance(this);
        iMainPresenter   = new MainPresenter(this);
    }

    public void initActions(){
        et_search_med.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    hideKeyboard();
                    getListMedicamentos();
                    return true;
                }
                return false;
            }
        });
    }

    @OnClick(R.id.tv_limpiar_act_main)
    public void onClickLimpiar() {
        et_search_med.setText("");
        medicamentosList.clear();
        da_medicamentos.notifyDataSetChanged();
        sv_medicamentos.setVisibility(View.GONE);
        rv_central_image.setVisibility(View.VISIBLE);
        tv_limpiar.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.iv_filter_act_main)
    public void onClickFiltro() {
        filter.show();
    }

    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(getApplication().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void getListMedicamentos(){
        String med_nombre = et_search_med.getText().toString().toLowerCase();
        if(med_nombre.length() != 0){
            loading.show();
            iMainPresenter.getListMedicamentosByNombre(med_nombre);
        }else{
            sv_medicamentos.setVisibility(View.GONE);
            rv_central_image.setVisibility(View.VISIBLE);
            tv_limpiar.setVisibility(View.INVISIBLE);
            medicamentosList.clear();
            da_medicamentos.notifyDataSetChanged();
        }
    }

    @Override
    public void setUbigeoValue(String ubigeoVal){
        this.ubigeo = ubigeoVal;
    }

    @Override
    public void onSuccessListMedicamentosByNombre(List<MedicamentoModel> listMedicamentosRet){
        medicamentosList.clear();
        medicamentosList.addAll(listMedicamentosRet);
        da_medicamentos.notifyDataSetChanged();
        if(listMedicamentosRet.size() > 0){
            sv_medicamentos.setVisibility(View.VISIBLE);
            rv_central_image.setVisibility(View.GONE);
            tv_limpiar.setVisibility(View.VISIBLE);
        }else{
            sv_medicamentos.setVisibility(View.GONE);
            rv_central_image.setVisibility(View.VISIBLE);
            tv_limpiar.setVisibility(View.INVISIBLE);
        }
        loading.dismiss();
    }

    @Override
    public void goToPriceListActivity(MedicamentoModel item){
        String[] id_parts = item.getId().split("@");

        Intent intent = new Intent(this, PriceListActivity.class);
        intent.putExtra("EXTRA_NAME", item.getName());
        intent.putExtra("EXTRA_GROUP_MED", id_parts[0]);
        intent.putExtra("EXTRA_CON_MED", id_parts[2].replace(' ', '*'));
        intent.putExtra("EXTRA_FFS_MED", id_parts[3]);
        if(ubigeo.isEmpty()){
            intent.putExtra("EXTRA_UBIGEO_MED", "15");
        } else {
            intent.putExtra("EXTRA_UBIGEO_MED", ubigeo);
        }
        intent.putExtra("EXTRA_CAD_MED", item.getName().replace(' ', '*'));
        startActivity(intent);
    }

    @Override
    public void onError(String msg) {
        loading.dismiss();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Timber.e("Error message: %s", msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);
        unbinder.unbind();
    }
}