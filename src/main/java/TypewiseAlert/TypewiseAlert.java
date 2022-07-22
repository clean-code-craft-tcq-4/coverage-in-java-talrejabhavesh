package TypewiseAlert;

public class TypewiseAlert 
{
    
  
  public enum BreachType {
    NORMAL,
    TOO_LOW,
    TOO_HIGH
  };
  
  
  
  public static BreachType inferBreach(double value, double lowerLimit, double upperLimit) {
    if(value < lowerLimit) {
      return BreachType.TOO_LOW;
    }
    if(value > upperLimit) {
      return BreachType.TOO_HIGH;
    }
    return BreachType.NORMAL;
  }
  
  
  
  
  public enum CoolingType {
    PASSIVE_COOLING,
    HI_ACTIVE_COOLING,
    MED_ACTIVE_COOLING
  };
  
  
  /* it returns lower and upper limit of temperatures as an integer array*/
  public static int[] classifyTemperatureBreach(CoolingType coolingType) {
    if(coolingType==CoolingType.PASSIVE_COOLING) {
      return new int[] {0,35};
    }
    else if(coolingType==CoolingType.HI_ACTIVE_COOLING) {
      return new int[] {0,45};
    }
    else {
      return new int[] {0,40};
    }
  }
  
  
  
  public enum AlertTarget{
    TO_CONTROLLER,
    TO_EMAIL
  };
  
  
  
  static class BatteryCharacter {
    public CoolingType coolingType;
    public String brand;
  }
  
  
  
  
  public static BreachType checkBreachType(
      BatteryCharacter batteryChar, double temperatureInC) {
    
    int[] limits=classifyTemperatureBreach(
        batteryChar.coolingType);  
    BreachType breachType = inferBreach(temperatureInC,limits[0],limits[1]);

  return breachType;
  }
  

  
  public static boolean sendAlert(AlertTarget alertTarget, BreachType breachType) {
    if (alertTarget == AlertTarget.TO_CONTROLLER) {
      sendToController(breachType);
      return true;
    }
    else if(alertTarget == AlertTarget.TO_EMAIL) {
      sendToEmail(breachType);
      return true;
    }
    return false;
  }
  
  
  
  public static void sendToController(BreachType breachType) {
    int header = 0xfeed;
    System.out.printf("%s : %s\n", header, breachType);
  }
  
  
  
  
  public static void sendToEmail(BreachType breachType) {
    String recepient = "a.b@c.com";
    if(breachType!=BreachType.NORMAL) {
        System.out.printf("To: %s\n", recepient);
        System.out.println("Hi, the temperature is too "+ breachType +"\n");
    }
  }
 

}
