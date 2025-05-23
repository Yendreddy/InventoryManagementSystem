//package org.project.InventoryManagementSystemImpl.config;
//
//import org.modelmapper.ModelMapper;
//import org.project.InventoryManagementSystemImpl.dto.OrdersPlacedDTO;
//import org.project.InventoryManagementSystemImpl.entity.OrdersPlaced;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MapperConfig {
//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper mapper = new ModelMapper();
//
//        // Customize mapping for OrdersPlaced ➝ OrdersPlacedDTO
//        mapper.typeMap(OrdersPlaced.class, OrdersPlacedDTO.class).addMappings(mapper -> {
//            mapper.map(src -> src.getCustomer().getCustomer_id(), OrdersPlacedDTO::setCustomer_id);
//            mapper.map(OrdersPlaced::getProduct_id, OrdersPlacedDTO::setProduct_id);
//            mapper.map(OrdersPlaced::getCategory_id, OrdersPlacedDTO::setCategory_id);
//        });
//
//        return mapper;
//    }
//}
