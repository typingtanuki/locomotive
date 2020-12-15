package com.github.typingtanuki.locomotive.steps.system;

import com.github.typingtanuki.locomotive.ppa.Ppa;
import com.github.typingtanuki.locomotive.ppa.Ppas;
import com.github.typingtanuki.locomotive.steps.AbstractPpaStep;

public class EnablePpaOibafStep extends AbstractPpaStep {
    public EnablePpaOibafStep() {
        super(Ppas.oibaf());
    }
}
