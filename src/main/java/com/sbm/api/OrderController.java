package com.sbm.api;

import com.sbm.controller.OrderRequest;
import com.sbm.dto.model.response.Response;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Order Details Controller
 *
 * @author HCL
 */
@RequestMapping("/api/v1")
public interface OrderController {
    /**
     * Create order.
     * @param orderRequest
     * @return Response
     */
    @PostMapping("/register")
    public Response registerOrder(@Valid @RequestBody OrderRequest orderRequest);

    /**
     * Delete order.
     * @param orderRequest
     * @return Response
     */
    @DeleteMapping("/cancel")
    public Response cancelOrder(@Valid @RequestBody OrderRequest orderRequest);

    /**
     * Get all order details.
     * @return Response
     */
    @GetMapping("/summary")
    public Response getSummary();
}
