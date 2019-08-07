package com.project.test.Core.PriceList.interactor;

import com.project.common.CommonUtil;
import com.project.test.Core.PriceList.presenter.PriceListPresenter;
import com.project.test.Core.PriceList.presenter.iPriceListPresenter;
import com.project.test.Networking.RetrofitClient;
import com.project.test.Networking.models.response.ResultPrecioMedicamentos;
import com.project.test.Networking.services.Networking;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PriceListInteractor {

    Networking networking = RetrofitClient.getClient(CommonUtil.BASE_URL).create(Networking.class);
    iPriceListPresenter iPriceListPresenter;

    public PriceListInteractor(PriceListPresenter presenter){
        this.iPriceListPresenter = presenter;
    }

    public void getListPriceByMedicamento(String grupo, String con, String ffs, String ubigeo, String cad){
        networking.getListPreciosMedicamento(grupo, con, ffs, ubigeo, cad).enqueue(new Callback<ResultPrecioMedicamentos>() {
            @Override
            public void onResponse(Call<ResultPrecioMedicamentos> call, Response<ResultPrecioMedicamentos> response) {
                if(response.isSuccessful()) {
                    iPriceListPresenter.onSuccessListPriceByMedicamento(response.body().getAaData());
                }
            }

            @Override
            public void onFailure(Call<ResultPrecioMedicamentos> call, Throwable t) {
                iPriceListPresenter.onError("Unable to submit post to API.");
            }
        });
    }
}
