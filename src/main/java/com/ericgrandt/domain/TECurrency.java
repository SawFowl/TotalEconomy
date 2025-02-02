package com.ericgrandt.domain;

import com.google.common.base.Objects;
import java.math.BigDecimal;
import java.math.RoundingMode;
import net.kyori.adventure.text.Component;
import org.spongepowered.api.service.economy.Currency;

public class TECurrency implements Currency {
    private final int id;
    private final String singular;
    private final String plural;
    private final String symbol;
    private final int numFractionDigits;
    private final boolean isDefault;

    public TECurrency(int id, String singular, String plural, String symbol, int numFractionDigits, boolean isDefault) {
        this.id = id;
        this.singular = singular;
        this.plural = plural;
        this.symbol = symbol;
        this.numFractionDigits = numFractionDigits;
        this.isDefault = isDefault;
    }

    @Override
    public Component displayName() {
        return Component.text(singular);
    }

    @Override
    public Component pluralDisplayName() {
        return Component.text(plural);
    }

    @Override
    public Component symbol() {
        return Component.text(symbol);
    }

    @Override
    public Component format(BigDecimal amount, int numFractionDigits) {
        BigDecimal scaledAmount = amount.setScale(numFractionDigits, RoundingMode.HALF_DOWN);
        String formattedAmount = String.format("$%s", scaledAmount);

        return Component.text(formattedAmount);
    }

    @Override
    public int defaultFractionDigits() {
        return numFractionDigits;
    }

    @Override
    public boolean isDefault() {
        return isDefault;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TECurrency other = (TECurrency) o;
        return id == other.id
            && Objects.equal(singular, other.singular)
            && Objects.equal(plural, other.plural)
            && Objects.equal(symbol, other.symbol)
            && numFractionDigits == other.numFractionDigits
            && isDefault == other.isDefault;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, singular, plural, symbol, numFractionDigits, isDefault);
    }
}
