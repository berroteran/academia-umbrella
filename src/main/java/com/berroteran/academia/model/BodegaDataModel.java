package com.berroteran.academia.model;

import com.berroteran.academia.entity.Bodega;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class BodegaDataModel extends ListDataModel<Bodega> implements SelectableDataModel<Bodega>{

    public BodegaDataModel() {
    }
    public BodegaDataModel(List<Bodega>data) {
        super(data);
    }

    @Override
    public Bodega  getRowData(String rowKey) {
        List<Bodega> bodegaList = (List<Bodega>) getWrappedData();
        for (Bodega bodega : bodegaList) {
            if (bodega.getIdbodega().equals(rowKey)) {
                return bodega;
            }
        }
        return null;
    }
    @Override
    public Object getRowKey(Bodega bodega) {
        return bodega.getIdbodega();
    }


}