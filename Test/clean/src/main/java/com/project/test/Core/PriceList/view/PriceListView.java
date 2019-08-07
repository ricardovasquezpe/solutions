package com.project.test.Core.PriceList.view;

import java.util.List;

public interface PriceListView {

    void onError(String msg);

    void onSuccessListPriceByMedicamento(List<Object> precios);

    void onMapClicked(String idp, String ide);

}
