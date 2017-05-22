// -------------------------------------------------------------------------------------------------
//
// Copyright (C) 2017 HERE Global B.V. and/or its affiliated companies. All rights reserved.
//
// This software, including documentation, is protected by copyright controlled by
// HERE Global B.V. All rights are reserved. Copying, including reproducing, storing,
// adapting or translating, any or all of this material requires the prior written
// consent of HERE Global B.V. This material also contains confidential information,
// which may not be disclosed to others without prior written consent of HERE Global B.V.
//
// -------------------------------------------------------------------------------------------------

#include "RuntimeImpl.h"

namespace navigation {

RuntimeStub::CreateRuntimeDefaultsExpected RuntimeStub::createRuntimeDefaults(  )
{
    auto runtime = std::unique_ptr<internal::RuntimeImpl>(new internal::RuntimeImpl());

    return RuntimeStub::CreateRuntimeDefaultsExpected::result(std::move(runtime));
}

RuntimeStub::CreateRuntimeWithConfigExpected RuntimeStub::createRuntimeWithConfig( const std::string& configFile )
{
    auto runtime = std::unique_ptr<internal::RuntimeImpl>(new internal::RuntimeImpl());

    return RuntimeStub::CreateRuntimeWithConfigExpected::result(std::move(runtime));
}

RuntimeStub::CreateRuntimeFullExpected RuntimeStub::createRuntimeFull( const std::string& configFile, const std::string& initialProfile, const std::string& initialLanguage )
{
    auto runtime = std::unique_ptr<internal::RuntimeImpl>(new internal::RuntimeImpl());

    return RuntimeStub::CreateRuntimeFullExpected::result(std::move(runtime));
}

namespace internal {

NavigationTypes::ErrorCode RuntimeImpl::shutdown()
{
}

RuntimeStub::ShutdownCompletedExpected RuntimeImpl::shutdownCompleted() const
{
}

NavigationTypes::ErrorCode RuntimeImpl::resume()
{
}

NavigationTypes::ErrorCode RuntimeImpl::stop()
{
}

NavigationTypes::ErrorCode RuntimeImpl::registerModuleFactory(
        const std::shared_ptr<ModuleFactoryStub> &moduleFactory)
{
    if (moduleFactory == nullptr) {
        return NavigationTypes::ErrorCode::Unknown; // Invalid arguments
    }
    m_factories.push_back( moduleFactory );
    return NavigationTypes::ErrorCode::None;
}

RuntimeStub::CreateModuleExpected RuntimeImpl::createModule(const std::string &name)
{
    for (auto factory : m_factories) {
        if (factory->getName() == name) {
            auto module_result = factory->createModule();
            return RuntimeStub::CreateModuleExpected::result(std::move(module_result.take_result()));
        }
    }

    return RuntimeStub::CreateModuleExpected::fail(NavigationTypes::ErrorCode::Unknown); // Not found
}

std::shared_ptr<ProfileManagerStub> RuntimeImpl::getProfileManager() const
{
}

std::shared_ptr<SystemInformationStub> RuntimeImpl::getSystemInformation() const
{
}

std::shared_ptr<NetworkConfigurationStub> RuntimeImpl::getNetworkConfiguration() const
{
}

std::string RuntimeImpl::getVersion() const
{
}

std::string RuntimeImpl::getApplicationId() const
{
}

std::string RuntimeImpl::getApplicationCode() const
{
}

std::string RuntimeImpl::getBrandVariant() const
{
}

void RuntimeImpl::setBrandVariant(const std::string &brandVariant)
{
}

int16_t RuntimeImpl::getRestrictedSetId() const
{
}

void RuntimeImpl::setRestrictedSetId(const int16_t restrictedSetId)
{
}

NavigationTypes::CountryCode RuntimeImpl::getPoliticalMapView() const
{
}

void RuntimeImpl::setPoliticalMapView(const NavigationTypes::CountryCode &politicalMapView)
{
}

NavigationTypes::Language RuntimeImpl::getLanguage() const
{
    return "foo";
}

void RuntimeImpl::setLanguage(const NavigationTypes::Language &language)
{
    notifyLanguageChanged(language);
}

} // internal

} // navigation
