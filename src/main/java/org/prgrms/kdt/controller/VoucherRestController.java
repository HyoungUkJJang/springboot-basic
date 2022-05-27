package org.prgrms.kdt.controller;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.prgrms.kdt.controller.dto.CreateVoucherRequest;
import org.prgrms.kdt.controller.dto.VoucherResponse;
import org.prgrms.kdt.controller.dto.VoucherSearchCriteria;
import org.prgrms.kdt.controller.dto.VouchersResponse;
import org.prgrms.kdt.voucher.model.Voucher;
import org.prgrms.kdt.voucher.model.VoucherType;
import org.prgrms.kdt.voucher.service.VoucherService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherRestController {

    private final VoucherService voucherService;

    public VoucherRestController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping
    public List<VouchersResponse> vouchers(VoucherSearchCriteria criteria) {
        if (criteria.getType() != null || criteria.getStartDate() != null || criteria.getEndDate() != null) {
            return this.voucherService.searchVouchers(criteria)
                .stream().map(VouchersResponse::of).toList();
        }

        return this.voucherService.getVouchers().stream()
            .map(VouchersResponse::of)
            .toList();
    }

    @GetMapping("/{id}")
    public VoucherResponse voucherDetail(@PathVariable UUID id) {
        Voucher findVoucher = this.voucherService.getVoucher(id);

        return new VoucherResponse(
            findVoucher.getId(),
            findVoucher.getValue(),
            findVoucher.getVoucherType(),
            findVoucher.getCreatedAt()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID create(@Valid @RequestBody CreateVoucherRequest createVoucher) {
        return this.voucherService.makeVoucher(createVoucher.getType(), createVoucher.getValue())
            .getId();
    }

    @PutMapping("{id}/update")
    public void updateVoucher(@PathVariable UUID id, long value) {
        this.voucherService.updateVoucher(id, value);
    }

    @DeleteMapping("{id}/delete")
    public void deleteVoucher(@PathVariable UUID id) {
        this.voucherService.deleteVoucher(id);
    }

}
