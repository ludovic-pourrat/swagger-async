package io.swagger.v2.async.filter.resources;

import io.swagger.v2.async.filter.AbstractSpecFilter;

/**
 * signals to remove unreferenced definitions.
 **/
public class RemoveUnreferencedDefinitionsFilter extends AbstractSpecFilter {
    @Override
    public boolean isRemovingUnreferencedDefinitions() {
        return true;
    }
}