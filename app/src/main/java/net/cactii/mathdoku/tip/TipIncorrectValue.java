package net.cactii.mathdoku.tip;

import android.content.Context;

import net.cactii.mathdoku.Preferences;
import net.cactii.mathdoku.R;

public class TipIncorrectValue extends TipDialog {

    private static final TipPriority TIP_PRIORITY = TipPriority.HIGH;
    public static String TIP_NAME = "Tip.TipIncorrectValue.DisplayAgain";

    /**
     * Creates a new tip dialog which explains that an incorrect value has been
     * entered.
     *
     * @param context The activity in which this tip has to be shown.
     */
    public TipIncorrectValue(Context context) {
        super(context, TIP_NAME, TIP_PRIORITY);

        build(
                context.getResources().getString(
                        R.string.dialog_tip_incorrect_value_title),
                context.getResources().getString(
                        R.string.dialog_tip_incorrect_value_text), null).show();
    }

    /**
     * Checks whether this tip has to be displayed. Should be called statically
     * before creating this object.
     *
     * @param preferences Preferences of the activity for which has to be checked
     *                    whether this tip should be shown.
     * @return
     */
    public static boolean toBeDisplayed(Preferences preferences) {
        // Note: No time restriction has been set on this tip. Display each time if applicable.

        // Determine on basis of preferences whether the tip should be shown.
        return TipDialog
                .getDisplayTipAgain(preferences, TIP_NAME, TIP_PRIORITY);
    }

    /**
     * Ensure that this tip will not be displayed (again).
     */
    public static void doNotDisplayAgain(Preferences preferences) {
        // Determine on basis of preferences whether the tip should be shown.
        preferences.setTipDoNotDisplayAgain(TIP_NAME);
    }
}
