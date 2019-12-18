package nachos.proj1;

public class FoodOrder implements Runnable { //extend manual
	private String customerName;
	private String foodName;
	private int foodQuantity;
	
	public FoodOrder() {
		// TODO Auto-generated constructor stub
	}

	//alt shift s, ctrl shift f buat rapihin whitespacenya
	public FoodOrder(String customerName, String foodName, int foodQuantity) {
		super();
		this.customerName = customerName;
		this.foodName = foodName;
		this.foodQuantity = foodQuantity;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodQuantity() {
		return foodQuantity;
	}

	public void setFoodQuantity(int foodQuantity) {
		this.foodQuantity = foodQuantity;
	}

	//add unimplemented methods
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Serving...");
		
		try {
			Thread.sleep(foodQuantity * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(customerName + "'s order is served!");
	}

}
