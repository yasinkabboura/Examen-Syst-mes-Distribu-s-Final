package coreapi

import java.time.Instant
import java.util.*

data class CustomerDTO(
    var customerId: String="",
    var nom : String ="",
    var address : String ="",
    var mail :String ="",
    var tel : String ="",
    );
data class EditCustomerDTO(
        var address : String ="",
        var mail :String ="",
        var tel : String ="",
);
data class CustomerResponseDTO(
    var customerId : String ="",
    var nom : String ="",
    var address : String ="",
    var mail :String ="",
    var tel : String ="",
);
data class ProductResponseDTO(
        var productId: String="",
        var nom : String ="",
        var prix : Double,
        var quntity :Int,
        var status: ProductStatus
);
data class CategoryResponseDTO(
        var catID:String="",
        var nom : String ="",
        var description : String ="",
);

data class EventDataResponseDTO<T>(
    var type : String="",
    var eventData : T ,
);
data class ProductDTO(
        var productId: String="",
        var catID:String="",
        var nom : String ="",
        var prix : Double,
        var quntity :Int,
        var status: ProductStatus = ProductStatus.DISPONIBLE
);
data class CategoryDTO(
        var catID:String="",
        var nom : String ="",
        var description : String ="",

);




