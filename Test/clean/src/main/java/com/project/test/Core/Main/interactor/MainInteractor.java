package com.project.test.Core.Main.interactor;

import com.project.common.CommonUtil;
import com.project.test.Core.Main.presenter.MainPresenter;
import com.project.test.Core.Main.presenter.iMainPresenter;
import com.project.test.Networking.RetrofitClient;
import com.project.test.Networking.models.request.MedicamentosBody;
import com.project.test.Networking.models.response.ResultMedicamentosModel;
import com.project.test.Networking.services.Networking;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractor {

    iMainPresenter iMainPresenter;
    Networking networking = RetrofitClient.getClient(CommonUtil.BASE_URL).create(Networking.class);

    public MainInteractor(MainPresenter presenter){
        this.iMainPresenter = presenter;
    }

    public void getListMedicamentosByNombre(MedicamentosBody body){
        networking.getMedicamentos(body).enqueue(new Callback<ResultMedicamentosModel>() {
            @Override
            public void onResponse(Call<ResultMedicamentosModel> call, Response<ResultMedicamentosModel> response) {
                if(response.isSuccessful()) {
                    iMainPresenter.onSuccessListMedicamentosByNombre(response.body().getMedicamentos());
                }
            }

            @Override
            public void onFailure(Call<ResultMedicamentosModel> call, Throwable t) {
                iMainPresenter.onError("Unable to submit post to API. Security errors");
            }
        });
    }

}
