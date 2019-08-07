package com.project.test.Core.Main.view;

import com.project.test.Networking.models.response.MedicamentoModel;

import java.util.List;

public interface MainView {

    void onSuccessListMedicamentosByNombre(List<MedicamentoModel> listMedicamentos);

    void goToPriceListActivity(MedicamentoModel item);

    void onError(String msg);

    void setUbigeoValue(String ubigeoVal);

}
