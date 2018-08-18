package com.trj.jk.web.controller.escrow;

import com.trj.commons.result.Result;

import com.trj.commons.result.Results;
import com.trj.jk.web.controller.BaseController;
import com.trj.jk.web.model.dto.EscrowConfirmDTO;
import com.trj.jk.web.model.dto.EscrowOpenDTO;
import com.trj.jk.web.service.escrow.IEscrowService;
import com.trj.jk.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 银行存管
 *
 * @author -ming-
 */
@RestController
@RequestMapping(value = {"/escrow"})
public class EscrowController extends BaseController {

    @Autowired
    private IEscrowService escrowService;

    /**
     * 银行存管开户
     *
     * @return
     */
    @PostMapping(value = {"/open"})
    public Result<EscrowOpenDTO> escrowOpen(byte clientType) {
        Integer uid = (Integer) SessionUtil.getUserLogonInfo();
        EscrowOpenDTO bean = escrowService.escrowOpen(uid, clientType);
        return Results.newSuccessResult(bean);
    }

    /**
     * 存管开户回调
     *
     * @return
     */
    @PostMapping(value = {"/confirm"})
    public Result<Boolean> escrowConfirm(EscrowConfirmDTO escrowConfirmDTO) {
        escrowService.escrowConfirm(escrowConfirmDTO);
        return Results.newSuccessResult(true);
    }

    /**
     * 银行存管开户
     *
     * @return
     */
    @PostMapping(value = {"/successUrl"})
    public void successUrl(HttpServletResponse response) {
        try {
            response.sendRedirect("https://uatjkwap.tourongjia.com/#!/applySuccess");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
