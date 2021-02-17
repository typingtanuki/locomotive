package com.github.typingtanuki.locomotive.components;

import static com.github.typingtanuki.locomotive.components.GlitchLabel.RANDOM;

public enum GlitchCharacter {
    a, b, c, d, e,
    f, g, h, i, j,
    k, l, m, n, o,
    p, q, r, s, t,
    u, v, w, x, y, z,
    n0('0'), n1('1'),
    n2('2'), n3('3'),
    n4('4'), n5('5'),
    n6('6'), n7('7'),
    n8('8'), n9('9'),
    SPACE(' ', false),
    DASH('-'),
    UNDER('_'),
    DOT('・'),
    ONO('斧'),
    SEKI('関'),
    RETURN_CARRIAGE('\r', false),
    NEW_LINE('\n', false);

    private final char value;
    private final boolean pickable;

    GlitchCharacter() {
        this('\0');
    }

    GlitchCharacter(char s) {
        this(s, true);
    }

    GlitchCharacter(char s, boolean pickable) {
        this.value = s;
        this.pickable = pickable;
    }

    public static GlitchCharacter from(char s) {
        try {
            return GlitchCharacter.valueOf(String.valueOf(s));
        } catch (IllegalArgumentException e) {
            // Continue
        }
        for (GlitchCharacter character : GlitchCharacter.values()) {
            if (character.getValue() == s) {
                return character;
            }
        }
        return GlitchCharacter.random();
    }

    public static GlitchCharacter random() {
        GlitchCharacter[] values = GlitchCharacter.values();
        boolean pickable = false;
        GlitchCharacter picked = GlitchCharacter.DASH;
        while (!pickable) {
            picked = values[RANDOM.nextInt(values.length)];
            pickable = picked.pickable;
        }
        return picked;
    }

    public char getValue() {
        if (value == '\0') {
            return name().charAt(0);
        }
        return value;
    }
}
