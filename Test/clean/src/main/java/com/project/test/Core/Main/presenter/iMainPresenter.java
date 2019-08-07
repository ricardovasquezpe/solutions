package com.project.test.Core.Main.presenter;

import com.project.test.Networking.models.response.MedicamentoModel;

import java.util.List;

public interface iMainPresenter {

    //REQUEST
    void getListMedicamentosByNombre(String nombre);

    //RESPOSE
    void onSuccessListMedicamentosByNombre(List<MedicamentoModel> medicamentos);
    void onError(String msg);

}
