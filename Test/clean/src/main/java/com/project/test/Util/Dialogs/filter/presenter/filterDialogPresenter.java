package com.project.test.Util.Dialogs.filter.presenter;

import com.project.test.Networking.models.request.DistritoBody;
import com.project.test.Networking.models.request.ProvinciaBody;
import com.project.test.Networking.models.response.UbigeoModel;
import com.project.test.Util.Dialogs.filter.interactor.filterDialogInteractor;
import com.project.test.Util.Dialogs.filter.view.filterDialog;
import com.project.test.Util.Dialogs.filter.view.filterDialogView;

import java.util.List;

public class filterDialogPresenter implements iFilterDialogPresenter{
    filterDialogView filterDialogView;
    filterDialogInteractor filterDialogInteractor = new filterDialogInteractor(this);

    public filterDialogPresenter(filterDialog dialog){
        filterDialogView = dialog;
    }

    @Override
    public void getListUbigeoBySearch(String codDep, String codProv) {
        if(codProv.isEmpty()){//BUSCA PROVINCIAS
            ProvinciaBody body = new ProvinciaBody();
            body.setCod_dep(codDep);
            filterDialogInteractor.getListProvinciasBySearch(body);
        }else{ //BUSCA DISTRITOS
            DistritoBody body = new DistritoBody();
            body.setCod_dep(codDep);
            body.setCod_prov(codProv);
            filterDialogInteractor.getListDistritosBySearch(body);
        }
    }

    @Override
    public void onSuccessListUbigeoBySearch(List<UbigeoModel> provincias, String type) {
        filterDialogView.onSuccessListUbigeoBySearch(provincias, type);
    }

    @Override
    public void onError(String msg) {
        filterDialogView.onError(msg);
    }
}
