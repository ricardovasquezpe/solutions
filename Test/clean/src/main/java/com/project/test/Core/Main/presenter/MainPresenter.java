package com.project.test.Core.Main.presenter;

import com.project.test.Core.Main.interactor.MainInteractor;
import com.project.test.Core.Main.view.MainActivity;
import com.project.test.Core.Main.view.MainView;
import com.project.test.Networking.models.request.MedicamentosBody;
import com.project.test.Networking.models.response.MedicamentoModel;

import java.util.List;

public class MainPresenter implements iMainPresenter {

    MainView mainView;
    MainInteractor mainInteractor = new MainInteractor(this);

    public MainPresenter(MainActivity activity){
        this.mainView = activity;
    }

    public void getListMedicamentosByNombre(String nombre){
        MedicamentosBody med = new MedicamentosBody();
        med.setAvanzado("");
        med.setTerm(nombre);
        mainInteractor.getListMedicamentosByNombre(med);
    }

    @Override
    public void onSuccessListMedicamentosByNombre(List<MedicamentoModel> medicamentos) {
        mainView.onSuccessListMedicamentosByNombre(medicamentos);
    }

    @Override
    public void onError(String msg) {
        mainView.onError(msg);
    }

}
