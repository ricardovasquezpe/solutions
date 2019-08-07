package com.project.test.Util.Dialogs.filter.presenter;

import com.project.test.Networking.models.response.UbigeoModel;
import java.util.List;

public interface iFilterDialogPresenter {
    //REQUEST
    void getListUbigeoBySearch(String codDep, String codProv);

    //RESPOSE
    void onSuccessListUbigeoBySearch(List<UbigeoModel> ubigeos, String type);
    void onError(String msg);

}
