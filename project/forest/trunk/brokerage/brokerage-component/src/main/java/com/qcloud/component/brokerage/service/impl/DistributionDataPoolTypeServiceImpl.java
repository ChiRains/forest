package com.qcloud.component.brokerage.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.brokerage.exception.BrokerageException;
import com.qcloud.component.brokerage.service.DistributionDataPoolTypeService;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.publicdata.IntKeyValue;

@Service
public class DistributionDataPoolTypeServiceImpl implements DistributionDataPoolTypeService {

    @Autowired
    private ParameterClient parameterClient;
    private static final String BROKERAGE_DAGA_POOL_TYPE = "brokerage-data-pool-type";
    
    @PostConstruct
    public void init(){
        final String type=parameterClient.get(BROKERAGE_DAGA_POOL_TYPE);
        if(StringUtils.isEmpty(type)){
            throw new BrokerageException("请初始化参数："+BROKERAGE_DAGA_POOL_TYPE);
        }
        List<IntKeyValue> list=listDataPoolType();
        if(CollectionUtils.isEmpty(list)){
            throw new BrokerageException("请初始化参数："+BROKERAGE_DAGA_POOL_TYPE);
        }
    }
    
    @Override
    public List<IntKeyValue> listDataPoolType() {

        final String typeStr=parameterClient.get(BROKERAGE_DAGA_POOL_TYPE);
        return listDataPoolType(typeStr);
    }
    
    public List<IntKeyValue> listDataPoolType(String typeStr){
        if(StringUtils.isEmpty(typeStr)){
            throw new BrokerageException("请初始化参数："+BROKERAGE_DAGA_POOL_TYPE);
        }
        String[] strs=typeStr.split(";");
        List<IntKeyValue> list=new ArrayList<IntKeyValue>();
        for (String str : strs) {
            final String[] items=str.split(":");
            if(items==null||items.length!=2){
                throw new BrokerageException("请初始化参数："+BROKERAGE_DAGA_POOL_TYPE);
            }
            final int key=Integer.valueOf(items[0]);
            final String value=items[1];
            list.add(new IntKeyValue() {
                
                @Override
                public String getValue() {
                
                    return value;
                }
                
                @Override
                public long getKey() {
                
                    return key;
                }
            });
        }
        return list;
    } 
    
    
}
