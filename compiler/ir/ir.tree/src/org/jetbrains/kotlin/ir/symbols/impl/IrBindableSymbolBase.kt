/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.ir.symbols.impl

import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.symbols.*

abstract class IrBindableSymbolBase<out D : DeclarationDescriptor, B : IrSymbolOwner>(
        override val descriptor: D
) : IrBindableSymbol<D, B> {
    private var _owner: B? = null
    override val owner: B
        get() = _owner ?: throw IllegalStateException("Symbol for $descriptor is unbound")

    override fun bind(owner: B) {
        if (_owner == null)
            _owner = owner
        else
            throw IllegalStateException("Symbol for $descriptor is already bound")
    }
}

class IrAnonymousInitializerSymbolImpl(descriptor: ClassDescriptor) :
        IrBindableSymbolBase<ClassDescriptor, IrAnonymousInitializer>(descriptor),
        IrAnonymousInitializerSymbol

class IrClassSymbolImpl(descriptor: ClassDescriptor) :
        IrBindableSymbolBase<ClassDescriptor, IrClass>(descriptor),
        IrClassSymbol

class IrEnumEntrySymbolImpl(descriptor: ClassDescriptor) :
        IrBindableSymbolBase<ClassDescriptor, IrEnumEntry>(descriptor),
        IrEnumEntrySymbol

class IrFileSymbolImpl(descriptor: PackageFragmentDescriptor) :
        IrBindableSymbolBase<PackageFragmentDescriptor, IrFile>(descriptor),
        IrFileSymbol

class IrFieldSymbolImpl(descriptor: PropertyDescriptor) :
        IrBindableSymbolBase<PropertyDescriptor, IrField>(descriptor),
        IrFieldSymbol

class IrTypeParameterSymbolImpl(descriptor: TypeParameterDescriptor) :
        IrBindableSymbolBase<TypeParameterDescriptor, IrTypeParameter>(descriptor),
        IrTypeParameterSymbol

class IrValueParameterSymbolImpl(descriptor: ParameterDescriptor) :
        IrBindableSymbolBase<ParameterDescriptor, IrValueParameter>(descriptor),
        IrValueParameterSymbol

class IrVariableSymbolImpl(descriptor: VariableDescriptor) :
        IrBindableSymbolBase<VariableDescriptor, IrVariable>(descriptor),
        IrVariableSymbol

class IrSimpleFunctionSymbolImpl(descriptor: FunctionDescriptor) :
        IrBindableSymbolBase<FunctionDescriptor, IrSimpleFunction>(descriptor),
        IrSimpleFunctionSymbol

class IrConstructorSymbolImpl(descriptor: ClassConstructorDescriptor) :
        IrBindableSymbolBase<ClassConstructorDescriptor, IrConstructor>(descriptor),
        IrConstructorSymbol