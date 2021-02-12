package com.swa.exchangerate.StartChoiceActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.ExecutionException;

@RunWith(MockitoJUnitRunner.class)
public class PresenterStartActivityTest {
    @Mock
    private ContractStartActivity.iView iView;
    @Mock
    private int id;
    @Mock
    String selected;
    @Mock
    String[] spinner;
    PresenterStartActivity presenterStartActivity;

    @Before
    public void setUp()throws Exception{
        presenterStartActivity=new PresenterStartActivity(iView);
    }


   @Test
    public void onButtonClick(){

   }
}