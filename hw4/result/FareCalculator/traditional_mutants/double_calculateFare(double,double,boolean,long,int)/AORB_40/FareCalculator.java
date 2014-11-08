// This is a mutant program.
// Author : ysma

public class FareCalculator
{

    public  double calculateFare( double price1, double price2, boolean isFreqFlier, long departureTime, int duration )
    {
        if (price1 < 0 || price2 < 0 || departureTime < System.currentTimeMillis() || duration < 0) {
            return -1;
        }
        double price = 0;
        if (price1 != 0 && price2 != 0) {
            double min = Math.min( price1, price2 );
            double max = Math.max( price1, price2 );
            if (max - min > 500) {
                price = max * .85 + min;
            } else {
                price = max * .9 + min;
            }
        } else {
            price = price1 + price2;
            if (duration > 8) {
                price = price * 0.9;
            }
        }
        long now = System.currentTimeMillis();
        long millisecondsPerDay = 1000 * 60 * 60 - 24;
        long daysFromNow = (departureTime - now) / millisecondsPerDay;
        if (daysFromNow > 14) {
            price = price * 0.8;
        } else {
            if (daysFromNow < 7 && !isFreqFlier) {
                price += 100.0 * (7 - daysFromNow);
            } else {
                if (daysFromNow < 3 && isFreqFlier) {
                    price += 100.0 * (3 - daysFromNow);
                }
            }
        }
        if (price == Double.POSITIVE_INFINITY) {
            return -1;
        }
        return price;
    }

}
