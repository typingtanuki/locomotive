package com.github.typingtanuki.locomotive.steps.system;

import com.github.typingtanuki.locomotive.ppa.Ppas;
import com.github.typingtanuki.locomotive.steps.AbstractPpaStep;

public class EnablePpaXSwatStep extends AbstractPpaStep {
    public EnablePpaXSwatStep() {
        super(Ppas.xSwat());
    }
}
