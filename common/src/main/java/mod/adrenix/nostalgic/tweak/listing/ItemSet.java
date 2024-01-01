package mod.adrenix.nostalgic.tweak.listing;

import mod.adrenix.nostalgic.tweak.TweakValidator;
import mod.adrenix.nostalgic.tweak.factory.TweakListing;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class ItemSet extends ItemListing<String, ItemSet> implements DeletableSet<String, ItemSet>
{
    /* Fields */

    private final LinkedHashSet<String> items = new LinkedHashSet<>();
    private final transient LinkedHashSet<String> deleted = new LinkedHashSet<>();

    /* Constructors */

    public ItemSet(ItemRule... rules)
    {
        this.rules.addAll(Arrays.asList(rules));
    }

    /**
     * Gson requires that classes it deserializes to contain a no-args constructor, which is why the following "unused"
     * constructor exists.
     */
    @SuppressWarnings("unused")
    public ItemSet()
    {
    }

    /* Building */

    /**
     * @param set Provide a starting {@link LinkedHashSet} for the values.
     */
    public ItemSet startWith(LinkedHashSet<String> set)
    {
        this.items.addAll(set);
        return this;
    }

    /* Methods */

    /**
     * @return The {@link LinkedHashSet} associated with this {@link ItemSet}.
     */
    @Override
    public LinkedHashSet<String> getSet()
    {
        return this.items;
    }

    /**
     * @return The {@link LinkedHashSet} of deleted elements within this {@link ItemSet}.
     */
    @Override
    public LinkedHashSet<String> getDeleted()
    {
        return this.deleted;
    }

    @Override
    public ItemSet create()
    {
        return new ItemSet(this.rules.toArray(new ItemRule[0])).startWith(this.items);
    }

    @Override
    public void copy(ItemSet list)
    {
        this.addAll(list.items);
    }

    @Override
    public void clear()
    {
        this.items.clear();
        this.deleted.clear();
    }

    @Override
    public boolean matches(ItemSet listing)
    {
        return this.items.equals(listing.items);
    }

    @Override
    @SuppressWarnings("SuspiciousMethodCalls")
    public boolean containsKey(Object object)
    {
        return this.items.contains(object);
    }

    @Override
    public boolean validate(TweakValidator validator, TweakListing<String, ItemSet> tweak)
    {
        return ListingValidator.set(this, this.items, validator, tweak);
    }

    @Override
    public Class<String> genericType()
    {
        return String.class;
    }

    @Override
    public String debugString()
    {
        return String.format("ItemSet{size:%s}", this.items.size());
    }

    @Override
    public String toString()
    {
        return this.debugString();
    }
}
