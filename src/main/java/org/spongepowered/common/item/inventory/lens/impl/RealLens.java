/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.common.item.inventory.lens.impl;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityLockable;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.common.item.inventory.adapter.InventoryAdapter;
import org.spongepowered.common.item.inventory.adapter.impl.VanillaAdapter;
import org.spongepowered.common.item.inventory.lens.Fabric;
import org.spongepowered.common.item.inventory.lens.SlotProvider;

/**
 * Lenses for real Inventories like {@link TileEntityLockable} and {@link Container}.
 *
 * <p>When possible this lens will return the real {@link InventoryAdapter} as opposed to some kind of Wrapper Adapter</p>
 */
@SuppressWarnings("rawtypes")
public abstract class RealLens extends AbstractLens<IInventory, ItemStack> {

    public RealLens(int base, int size, Class<? extends Inventory> adapterType, SlotProvider<IInventory, ItemStack> slots) {
        super(base, size, adapterType, slots);
    }

    public RealLens(int base, int size, InventoryAdapter<IInventory, ItemStack> adapter, SlotProvider<IInventory, ItemStack> slots) {
        super(base, size, adapter, slots);
    }

    @SuppressWarnings("unchecked")
    @Override
    public InventoryAdapter<IInventory, ItemStack> getAdapter(Fabric<IInventory> inv, Inventory parent) {
        IInventory base = inv.get(this.base);
        if (!(base instanceof InventoryAdapter)) {
            return new VanillaAdapter(inv, this, parent);
        }
        return ((InventoryAdapter) base);
    }
}
