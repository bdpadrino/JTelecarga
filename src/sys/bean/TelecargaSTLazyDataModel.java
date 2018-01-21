package sys.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import sys.dao.TelecargaSTDao;
import sys.dao.imp.TelecargaSTDaoImp;
import sys.model.TelecargaST;

import java.util.Comparator;

//PRIMEFACES EXAMPLE
public class TelecargaSTLazyDataModel extends LazyDataModel<TelecargaST>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7829922825872076002L;


	TelecargaSTDao ct = new TelecargaSTDaoImp();
	
	private List<TelecargaST> datasource;
    
    public TelecargaSTLazyDataModel(List<TelecargaST> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public TelecargaST getRowData(String rowKey) {
        for(TelecargaST car : datasource) {
            if(car.getId().toString().equals(rowKey))
                return car;
        }
        return null;
    }
 
    @Override
    public Object getRowKey(TelecargaST car) {
        return car.getId();
    }
    
    @Override
    public List<TelecargaST> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<TelecargaST> data = new ArrayList<TelecargaST>();
 
        //filter
        for(TelecargaST car : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(car.getClass().getField(filterProperty).get(car));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
	                    }
	                    else {
	                            match = false;
	                            break;
	                        }
                    } catch(Exception e) {
                    	System.out.println("Exception "+e.getMessage());
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(car);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
    
    public class LazySorter implements Comparator<TelecargaST> {
   	 
        private String sortField;
         
        private SortOrder sortOrder;
         
        public LazySorter(String sortField, SortOrder sortOrder) {
            this.sortField = sortField;
            this.sortOrder = sortOrder;
        }
     
        public int compare(TelecargaST car1, TelecargaST car2) {
            try {
                Object value1 = TelecargaST.class.getField(this.sortField).get(car1);
                Object value2 = TelecargaST.class.getField(this.sortField).get(car2);
     
                @SuppressWarnings({ "unchecked", "rawtypes" })
				int value = ((Comparable)value1).compareTo(value2);
                 
                return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
            }
            catch(Exception e) {
                throw new RuntimeException();
            }
        }
    }
}




