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
  
  
  
  public static int[] classifyTemperatureBreach(
      CoolingType coolingType) {
    int lowerLimit = 0;
    int upperLimit = 0;
    switch(coolingType) {
      case PASSIVE_COOLING:
        upperLimit = 35;
        break;
      case HI_ACTIVE_COOLING:
        upperLimit = 45;
        break;
      case MED_ACTIVE_COOLING:
        upperLimit = 40;
        break;
    }
    return new int[] {lowerLimit,upperLimit};
  }
  
  
  
  public enum AlertTarget{
    TO_CONTROLLER,
    TO_EMAIL
  };
  
  
  
  static class BatteryCharacter {
    public CoolingType coolingType;
    public String brand;
  }
  
  
  
  
  public static BreachType checkAndAlert(
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
    switch(breachType) {
      case TOO_LOW:
      case TOO_HIGH:
        System.out.printf("To: %s\n", recepient);
        System.out.println("Hi, the temperature is too "+ breachType +"\n");
        break;
      case NORMAL:
        break;
    }
  }
  
  
  
  
  


}
