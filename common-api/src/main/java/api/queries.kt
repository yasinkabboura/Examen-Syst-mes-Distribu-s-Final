package coreapi

class GetAllCustomersQuery();
data class GetCustomerById(
    val customerId:String,
);

class GetAllProductsQuery();
data class GetProductById(
        val customerId:String,
);
data class GetProductByCat(
        val catID:String,
);
class SubscribeToEventsQuery();
class GetAllCategoriesQuery();
data class GetCategoryById(
        val customerId:String,
);


