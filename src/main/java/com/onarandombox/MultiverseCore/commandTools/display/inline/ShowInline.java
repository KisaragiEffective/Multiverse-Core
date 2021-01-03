package com.onarandombox.MultiverseCore.commandTools.display.inline;

import com.onarandombox.MultiverseCore.commandTools.display.ContentFilter;
import com.onarandombox.MultiverseCore.commandTools.display.ShowRunnable;
import org.jetbrains.annotations.NotNull;

/**
 * Show the content inline, separated by comma.
 *
 * @param <C> A Display that extends {@link InlineDisplay}
 * @param <T> Content type to show.
 */
public abstract class ShowInline<C extends InlineDisplay<?, T>, T> extends ShowRunnable<C, T> {

    protected final StringBuilder contentBuilder;

    public ShowInline(@NotNull C display) {
        super(display);
        this.contentBuilder = new StringBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean hasContentToShow() {
        return contentBuilder.length() > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean validateContent() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showHeader() {
        if (this.display.getHeader() == null) {
            return;
        }

        this.display.getSender().sendMessage(this.display.getHeader());

        ContentFilter filter = this.display.getFilter();
        if (filter.hasFilter()) {
            this.display.getSender().sendMessage(String.format("[ %s ]", filter.getFormattedString()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showContent() {
        this.display.getSender().sendMessage(String.format("%s%s%s",
                this.display.getPrefix(), this.contentBuilder.toString(), this.display.getSuffix()));
    }
}
