package be.vdab.groenetenen.constraints;

import be.vdab.groenetenen.forms.VantotPostcodeForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VanTotPostcodeFormVanKleinerDanOfGelijkAanTotValidator implements ConstraintValidator<VanTotPostcodeFormVanKleinerDanOfGelijkAanTot, VantotPostcodeForm> {
    @Override
    public void initialize(VanTotPostcodeFormVanKleinerDanOfGelijkAanTot constraintAnnotation) {

    }

    @Override
    public boolean isValid(VantotPostcodeForm form, ConstraintValidatorContext context) {
        if (form.getVan() == null || form.getTot() == null){
            return true;
        }
        return form.getVan() <= form.getTot();
    }
}
