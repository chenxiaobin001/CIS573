package edu.upenn.cis573.flights;

public class FareCalculator {
	
	/**
	 * This method calculates the fare for a given flight.
	 * If 
	 * 
	 * 
	 * @param price1 The price of the first segment of the flight.
	 * @param price2 The price of the second segment of the flight, if any.
	 * @param isFreqFlier Whether or not the traveler should receive a frequent flier discount.
	 * @param departureTime Date (using Java representation) of the outbound flight.
	 * @param duration Number of days of this trip.
	 * @return The calculated fare.
	 */
	
	public double calculateFare(double price1, double price2, boolean isFreqFlier, long departureTime, int duration) {
		
		if (price1 < 0 || price2 < 0 || departureTime < 0 || duration < 0) return -1;
		
		double price = 0;
				
		// if there are two prices, reduce the more expensive one by 10%
		if (price1 != 0 && price2 != 0) {
			double min = Math.min(price1, price2);
			double max = Math.max(price1, price2);
			// unless the difference is more than 500, in which case, reduce it by 15%
			if (max - min > 500) {
				price = (max * .85) + min;
			}
			else price = (max * .9) + min;
		}
		// otherwise, the price is just the sum of the two, but if the duration is more than 8
		// days, then reduce the whole thing by 10%
		else {
			price = price1 + price2;
			if (duration > 8) price = price * 0.9;
		}
		
		// if the departure date is less than a week from now, add a surcharge
		// unless the traveler is a frequent flier, in which case the surcharge is only
		// if the departure date is less than three days from now
		// but if the departure date is more than 14 days from now, give a 20% discount
		long now = System.currentTimeMillis();
		long millisecondsPerDay = 1000 * 60 * 60 * 24;
		long daysFromNow = (departureTime - now)/millisecondsPerDay;
		if (daysFromNow > 14) {
			price = price * 0.8;
		}
		else if (daysFromNow < 7 && !isFreqFlier) {
			price += 100.0 * (7 - daysFromNow);  
		}
		else if (daysFromNow >= 7)  {
			if (daysFromNow < 3 && isFreqFlier) {
				price += 100.0 * (3 - daysFromNow);
			}
		}
		
		return price;
	}
	
}
