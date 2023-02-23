package TypewiseAlert;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TypewiseAlertTest 
{
   @Test
  public void infersBreachAsPerLimits()
  {
    assertTrue("test", TypewiseAlert.inferBreach(12, 20, 30) ==
        TypewiseAlert.BreachType.TOO_LOW);
    assertTrue("test", TypewiseAlert.inferBreach(22, 20, 30) ==
        TypewiseAlert.BreachType.NORMAL);
    assertTrue("test", TypewiseAlert.inferBreach(32, 20, 30) ==
        TypewiseAlert.BreachType.TOO_HIGH);
    
    
     
    assertTrue("test", TypewiseAlert.classifyTemperatureBreach(TypewiseAlert.CoolingType.PASSIVE_COOLING)[0] ==0);
    assertTrue("test", TypewiseAlert.classifyTemperatureBreach(TypewiseAlert.CoolingType.HI_ACTIVE_COOLING)[1] ==45);
    assertTrue("test", TypewiseAlert.classifyTemperatureBreach(TypewiseAlert.CoolingType.MED_ACTIVE_COOLING)[1] ==40);
 
    assertTrue("test", TypewiseAlert.sendAlert(TypewiseAlert.AlertTarget.TO_CONTROLLER,TypewiseAlert.BreachType.TOO_HIGH));
    assertTrue("test", TypewiseAlert.sendAlert(TypewiseAlert.AlertTarget.TO_EMAIL,TypewiseAlert.BreachType.TOO_LOW));
    assertTrue("test", TypewiseAlert.sendAlert(TypewiseAlert.AlertTarget.TO_EMAIL,TypewiseAlert.BreachType.TOO_HIGH));
    assertTrue("test", TypewiseAlert.sendAlert(TypewiseAlert.AlertTarget.TO_EMAIL,TypewiseAlert.BreachType.NORMAL));
    assertTrue("test", TypewiseAlert.sendAlert(null,TypewiseAlert.BreachType.NORMAL)==false);
    
    TypewiseAlert typewiseAlert=new TypewiseAlert();
    TypewiseAlert.BatteryCharacter batteryCharacter = new TypewiseAlert.BatteryCharacter ();
    batteryCharacter.coolingType=TypewiseAlert.CoolingType.PASSIVE_COOLING;
    batteryCharacter.brand="Sample";
    assertTrue("test", typewiseAlert.checkBreachType(batteryCharacter, 22)==TypewiseAlert.BreachType.NORMAL);
  }
  
}
