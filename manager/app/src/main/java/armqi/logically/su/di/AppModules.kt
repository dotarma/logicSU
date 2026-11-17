package armqi.logically.su.di

import armqi.logically.su.BuildConfig
import armqi.logically.su.data.AppSettingsRepository
import armqi.logically.su.data.application.ApplicationControlRepository
import armqi.logically.su.data.application.DynamicManagerRepository
import armqi.logically.su.data.count.CountRepository
import armqi.logically.su.data.download.DownloadRepository
import armqi.logically.su.data.file.ModuleFileRepository
import armqi.logically.su.data.flash.FlashRepository
import armqi.logically.su.data.kernel.KernelRepository
import armqi.logically.su.data.kernel.UmountRepository
import armqi.logically.su.data.logging.BugreportRepository
import armqi.logically.su.data.logging.SulogRepository
import armqi.logically.su.data.module.ModuleActionRepository
import armqi.logically.su.data.module.ModuleCatalogRepository
import armqi.logically.su.data.module.ModuleIconRepository
import armqi.logically.su.data.module.ModulePreferencesRepository
import armqi.logically.su.data.module.ModuleReleaseRepository
import armqi.logically.su.data.module.ModuleRepository
import armqi.logically.su.data.module.RepositorySourceStore
import armqi.logically.su.data.module.registry.KernelSuRegistryRepository
import armqi.logically.su.data.network.NetworkRequestRepository
import armqi.logically.su.data.network.NetworkStatusRepository
import armqi.logically.su.data.network.WebResourceRepository
import armqi.logically.su.data.packageinfo.AppIconDataSource
import armqi.logically.su.data.packageinfo.InstalledPackageCache
import armqi.logically.su.data.packageinfo.InstalledPackageRepository
import armqi.logically.su.data.packageinfo.RootServiceRepository
import armqi.logically.su.data.packageinfo.SuperUserRepository
import armqi.logically.su.data.profile.ProfileRepository
import armqi.logically.su.data.profile.ProfileTemplateRepository
import armqi.logically.su.data.script.ScriptRepository
import armqi.logically.su.data.settings.LocaleHelper
import armqi.logically.su.data.settings.LocaleRepository
import armqi.logically.su.data.settings.SettingsPlatformRepository
import armqi.logically.su.data.shell.KsuCliRepository
import armqi.logically.su.data.shell.ShortcutRepository
import armqi.logically.su.data.startup.ApplicationInitializationRepository
import armqi.logically.su.data.startup.StartupRepository
import armqi.logically.su.data.susfs.SuSFSConfigHelper
import armqi.logically.su.data.susfs.SuSFSRepository
import armqi.logically.su.data.system.HomeRuntimeRepository
import armqi.logically.su.data.system.HomeStateRepository
import armqi.logically.su.data.text.HanziToPinyin
import armqi.logically.su.data.theme.MonetCompatColorSource
import armqi.logically.su.data.theme.ThemeRepository
import armqi.logically.su.data.update.ManagerUpdateRepository
import armqi.logically.su.data.webui.WebUiRepository
import armqi.logically.su.domain.text.TextTransliterator
import armqi.logically.su.domain.usecase.AddModuleRepositoryUseCase
import armqi.logically.su.domain.usecase.AddUmountPathUseCase
import armqi.logically.su.domain.usecase.ApplyLanguageUseCase
import armqi.logically.su.domain.usecase.BackupAllowlistUseCase
import armqi.logically.su.domain.usecase.CalculateInstalledModuleSizeUseCase
import armqi.logically.su.domain.usecase.CheckFlashModuleMountUseCase
import armqi.logically.su.domain.usecase.CheckManagerUpdateUseCase
import armqi.logically.su.domain.usecase.CleanSulogUseCase
import armqi.logically.su.domain.usecase.ClearDynamicManagerUseCase
import armqi.logically.su.domain.usecase.ConfigureSuLogUseCase
import armqi.logically.su.domain.usecase.ControlAppUseCase
import armqi.logically.su.domain.usecase.DeleteProfileTemplateUseCase
import armqi.logically.su.domain.usecase.EnableSulogUseCase
import armqi.logically.su.domain.usecase.EnqueueDownloadUseCase
import armqi.logically.su.domain.usecase.EnqueueManagerUpdateUseCase
import armqi.logically.su.domain.usecase.EnsureManagerInstalledUseCase
import armqi.logically.su.domain.usecase.ExecuteFlashOperationUseCase
import armqi.logically.su.domain.usecase.ExecuteModuleActionUseCase
import armqi.logically.su.domain.usecase.ExportProfileTemplatesUseCase
import armqi.logically.su.domain.usecase.ExtractModuleIdUseCase
import armqi.logically.su.domain.usecase.ExtractModuleNameUseCase
import armqi.logically.su.domain.usecase.FetchRemoteTextUseCase
import armqi.logically.su.domain.usecase.GenerateBugreportUseCase
import armqi.logically.su.domain.usecase.GetAppProfileUseCase
import armqi.logically.su.domain.usecase.GetAppSepolicyUseCase
import armqi.logically.su.domain.usecase.GetBooleanPreferenceUseCase
import armqi.logically.su.domain.usecase.GetCatalogModuleUseCase
import armqi.logically.su.domain.usecase.GetDefaultUmountModulesUseCase
import armqi.logically.su.domain.usecase.GetHomeBasicInfoUseCase
import armqi.logically.su.domain.usecase.GetInstallEnvironmentUseCase
import armqi.logically.su.domain.usecase.GetKernelFeatureSettingsUseCase
import armqi.logically.su.domain.usecase.GetKernelStatusUseCase
import armqi.logically.su.domain.usecase.GetLastScriptRunUseCase
import armqi.logically.su.domain.usecase.GetManagerRuntimeInfoUseCase
import armqi.logically.su.domain.usecase.GetModuleReadmeUseCase
import armqi.logically.su.domain.usecase.GetModuleReleaseDetailsUseCase
import armqi.logically.su.domain.usecase.GetModuleUpdateChangelogUseCase
import armqi.logically.su.domain.usecase.GetPlatformFeatureStatusUseCase
import armqi.logically.su.domain.usecase.GetProfileTemplateUseCase
import armqi.logically.su.domain.usecase.GetStringPreferenceUseCase
import armqi.logically.su.domain.usecase.GetStringSetPreferenceUseCase
import armqi.logically.su.domain.usecase.GetSuSFSStatusUseCase
import armqi.logically.su.domain.usecase.GetSuperUserAppGroupUseCase
import armqi.logically.su.domain.usecase.ImportAllowlistUseCase
import armqi.logically.su.domain.usecase.ImportProfileTemplatesUseCase
import armqi.logically.su.domain.usecase.InitializeApplicationUseCase
import armqi.logically.su.domain.usecase.IsCatalogModuleCurrentUseCase
import armqi.logically.su.domain.usecase.IsLateLoadModeUseCase
import armqi.logically.su.domain.usecase.IsModuleUriAccessibleUseCase
import armqi.logically.su.domain.usecase.IsNetworkAvailableUseCase
import armqi.logically.su.domain.usecase.IsSoftRebootPreferredUseCase
import armqi.logically.su.domain.usecase.IsSystemLanguageSettingsUseCase
import armqi.logically.su.domain.usecase.LaunchSystemLanguageSettingsUseCase
import armqi.logically.su.domain.usecase.LoadModuleIconUseCase
import armqi.logically.su.domain.usecase.LoadSettingsPlatformUseCase
import armqi.logically.su.domain.usecase.ObserveAssetDownloadUseCase
import armqi.logically.su.domain.usecase.ObserveCatalogModulesUseCase
import armqi.logically.su.domain.usecase.ObserveDownloadUseCase
import armqi.logically.su.domain.usecase.ObserveDynamicManagerStateUseCase
import armqi.logically.su.domain.usecase.ObserveInstalledModulesUseCase
import armqi.logically.su.domain.usecase.ObserveKernelFlashUseCase
import armqi.logically.su.domain.usecase.ObserveModuleCatalogRefreshingUseCase
import armqi.logically.su.domain.usecase.ObserveProfileTemplateOfflineUseCase
import armqi.logically.su.domain.usecase.ObserveProfileTemplateRefreshingUseCase
import armqi.logically.su.domain.usecase.ObserveProfileTemplatesUseCase
import armqi.logically.su.domain.usecase.ObserveRepositorySourcesUseCase
import armqi.logically.su.domain.usecase.ObserveStartupStateUseCase
import armqi.logically.su.domain.usecase.ObserveSulogStateUseCase
import armqi.logically.su.domain.usecase.ObserveSuperUserStateUseCase
import armqi.logically.su.domain.usecase.ObserveUmountStateUseCase
import armqi.logically.su.domain.usecase.PeekOneshotScriptUseCase
import armqi.logically.su.domain.usecase.PrepareScriptUseCase
import armqi.logically.su.domain.usecase.RebootUseCase
import armqi.logically.su.domain.usecase.RefreshDynamicManagerUseCase
import armqi.logically.su.domain.usecase.RefreshInstalledModulesUseCase
import armqi.logically.su.domain.usecase.RefreshModuleCatalogUseCase
import armqi.logically.su.domain.usecase.RefreshProfileTemplatesUseCase
import armqi.logically.su.domain.usecase.RefreshSulogUseCase
import armqi.logically.su.domain.usecase.RefreshSuperUsersUseCase
import armqi.logically.su.domain.usecase.RefreshUmountPathsUseCase
import armqi.logically.su.domain.usecase.ReleaseScriptUseCase
import armqi.logically.su.domain.usecase.RemoveModuleRepositoryUseCase
import armqi.logically.su.domain.usecase.RemovePreferenceUseCase
import armqi.logically.su.domain.usecase.RemoveUmountPathUseCase
import armqi.logically.su.domain.usecase.RenameModuleRepositoryUseCase
import armqi.logically.su.domain.usecase.ResolveModuleUpdateUseCase
import armqi.logically.su.domain.usecase.RunScriptUseCase
import armqi.logically.su.domain.usecase.SaveModuleActionLogUseCase
import armqi.logically.su.domain.usecase.SaveProfileTemplateUseCase
import armqi.logically.su.domain.usecase.SelectDynamicManagerUseCase
import armqi.logically.su.domain.usecase.SetAppProfileUseCase
import armqi.logically.su.domain.usecase.SetAppSepolicyUseCase
import armqi.logically.su.domain.usecase.SetBooleanPreferenceUseCase
import armqi.logically.su.domain.usecase.SetDefaultUmountModulesUseCase
import armqi.logically.su.domain.usecase.SetKernelUmountEnabledUseCase
import armqi.logically.su.domain.usecase.SetManualDynamicManagerUseCase
import armqi.logically.su.domain.usecase.SetModuleEnabledUseCase
import armqi.logically.su.domain.usecase.SetModuleRemovedUseCase
import armqi.logically.su.domain.usecase.SetSelinuxHideEnabledUseCase
import armqi.logically.su.domain.usecase.SetShellSuAdbOnlyEnabledUseCase
import armqi.logically.su.domain.usecase.SetStringPreferenceUseCase
import armqi.logically.su.domain.usecase.SetStringSetPreferenceUseCase
import armqi.logically.su.domain.usecase.SetSuEnabledUseCase
import armqi.logically.su.domain.usecase.StartKernelFlashUseCase
import armqi.logically.su.domain.usecase.SuSFSConfigUseCase
import armqi.logically.su.domain.usecase.TakeModuleUriPermissionUseCase
import armqi.logically.su.domain.usecase.TransliterateTextUseCase
import armqi.logically.su.domain.usecase.UpdateAppearanceUseCase
import armqi.logically.su.domain.usecase.UpdateCachedModuleEnabledUseCase
import armqi.logically.su.domain.usecase.UpdatePlatformSettingUseCase
import armqi.logically.su.domain.usecase.ValidateSepolicyUseCase
import armqi.logically.su.ui.activity.util.ThemeUtils
import armqi.logically.su.ui.component.ZipFileDetector
import armqi.logically.su.ui.theme.BackgroundManager
import armqi.logically.su.ui.theme.CardConfig
import armqi.logically.su.ui.theme.ThemeConfig
import armqi.logically.su.ui.util.Shortcut
import armqi.logically.su.ui.viewmodel.AppProfileViewModel
import armqi.logically.su.ui.viewmodel.DynamicManagerViewModel
import armqi.logically.su.ui.viewmodel.ExecuteModuleActionViewModel
import armqi.logically.su.ui.viewmodel.FlashViewModel
import armqi.logically.su.ui.viewmodel.HomeViewModel
import armqi.logically.su.ui.viewmodel.InstallViewModel
import armqi.logically.su.ui.viewmodel.KernelFlashViewModel
import armqi.logically.su.ui.viewmodel.MainIntentViewModel
import armqi.logically.su.ui.viewmodel.ModuleDetailViewModel
import armqi.logically.su.ui.viewmodel.ModuleRepoViewModel
import armqi.logically.su.ui.viewmodel.ModuleViewModel
import armqi.logically.su.ui.viewmodel.RepositorySourcesViewModel
import armqi.logically.su.ui.viewmodel.ScriptRunViewModel
import armqi.logically.su.ui.viewmodel.SettingsViewModel
import armqi.logically.su.ui.viewmodel.SuSFSViewModel
import armqi.logically.su.ui.viewmodel.SulogViewModel
import armqi.logically.su.ui.viewmodel.SuperUserViewModel
import armqi.logically.su.ui.viewmodel.TemplateEditorViewModel
import armqi.logically.su.ui.viewmodel.TemplateViewModel
import armqi.logically.su.ui.viewmodel.UmountManagerScreenViewModel
import armqi.logically.su.ui.webui.MonetColorsProvider
import coil.ImageLoader
import java.io.File
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import me.zhanghai.android.appiconloader.coil.AppIconFetcher
import me.zhanghai.android.appiconloader.coil.AppIconKeyer
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val applicationScopeQualifier = named("applicationScope")

val coreModule = module {
    single<CoroutineScope>(applicationScopeQualifier) {
        CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }
    single {
        OkHttpClient.Builder()
            .cache(Cache(File(androidApplication().cacheDir, "okhttp"), 10L * 1024L * 1024L))
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .header("User-Agent", "logicSU/${BuildConfig.VERSION_CODE}")
                        .header("Accept-Language", Locale.getDefault().toLanguageTag())
                        .build(),
                )
            }
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .build()
    }
    single {
        val application = androidApplication()
        val iconSize = application.resources.getDimensionPixelSize(android.R.dimen.app_icon_size)
        ImageLoader.Builder(application)
            .components {
                add(AppIconKeyer())
                add(AppIconFetcher.Factory(iconSize, false, application))
            }
            .build()
    }
}

val repositoryModule = module {
    single { KsuCliRepository(androidApplication()) }
    singleOf(::CountRepository)
    singleOf(::InstalledPackageCache)
    singleOf(::AppIconDataSource)
    singleOf(::RootServiceRepository)
    singleOf(::InstalledPackageRepository)
    single {
        SuperUserRepository(
            application = get(),
            cache = get(),
            installedPackageRepository = get(),
            profileRepository = get(),
            applicationScope = get(applicationScopeQualifier),
        )
    }
    single {
        AppSettingsRepository(
            context = androidApplication(),
            applicationScope = get(applicationScopeQualifier),
        )
    }
    singleOf(::StartupRepository)
    single {
        ApplicationInitializationRepository(
            application = get(),
            imageLoader = get(),
            applicationScope = get(applicationScopeQualifier),
            flashRepository = get(),
            ksuCliRepository = get(),
        )
    }
    singleOf(::ManagerUpdateRepository)
    singleOf(::ApplicationControlRepository)
    singleOf(::DownloadRepository)
    single { FlashRepository(get(), get(applicationScopeQualifier), get(), get()) }
    singleOf(::KernelRepository)
    singleOf(::HomeRuntimeRepository)
    singleOf(::HomeStateRepository)
    singleOf(::NetworkStatusRepository)
    singleOf(::NetworkRequestRepository)
    singleOf(::DynamicManagerRepository)
    singleOf(::SulogRepository)
    singleOf(::BugreportRepository)
    singleOf(::UmountRepository)
    singleOf(::ModuleCatalogRepository)
    single { ModuleReleaseRepository(get(), get(applicationScopeQualifier)) }
    singleOf(::ModuleIconRepository)
    single { KernelSuRegistryRepository(get(), get(), get(), get()) }
    singleOf(::RepositorySourceStore)
    singleOf(::ModuleRepository)
    singleOf(::ModulePreferencesRepository)
    singleOf(::ModuleActionRepository)
    single { ScriptRepository(androidApplication(), get(), get(applicationScopeQualifier)) }
    singleOf(::WebResourceRepository)
    singleOf(::WebUiRepository)
    singleOf(::ModuleFileRepository)
    singleOf(::ProfileRepository)
    singleOf(::ProfileTemplateRepository)
    singleOf(::SuSFSConfigHelper)
    singleOf(::SuSFSRepository)
    singleOf(::MonetCompatColorSource)
    singleOf(::ThemeRepository)
    single {
        val themeRepository = get<ThemeRepository>()
        ThemeConfig(themeRepository::defaultSeedColor)
    }
    singleOf(::CardConfig)
    singleOf(::BackgroundManager)
    singleOf(::ThemeUtils)
    singleOf(::LocaleHelper)
    singleOf(::LocaleRepository)
    singleOf(::SettingsPlatformRepository)
    singleOf(::ShortcutRepository)
    singleOf(::Shortcut)
    singleOf(::MonetColorsProvider)
    singleOf(::ZipFileDetector)
    single { HanziToPinyin.create() } bind TextTransliterator::class
}

val useCaseModule = module {
    factoryOf(::InitializeApplicationUseCase)
    factoryOf(::GetHomeBasicInfoUseCase)
    factoryOf(::IsNetworkAvailableUseCase)
    factoryOf(::LoadSettingsPlatformUseCase)
    factoryOf(::UpdateAppearanceUseCase)
    factoryOf(::UpdatePlatformSettingUseCase)
    factoryOf(::GetPlatformFeatureStatusUseCase)
    factoryOf(::IsSoftRebootPreferredUseCase)
    factoryOf(::CheckManagerUpdateUseCase)
    factoryOf(::EnsureManagerInstalledUseCase)
    factoryOf(::RebootUseCase)
    factoryOf(::EnqueueDownloadUseCase)
    factoryOf(::EnqueueManagerUpdateUseCase)
    factoryOf(::ObserveDownloadUseCase)
    factoryOf(::ObserveAssetDownloadUseCase)
    factoryOf(::GetKernelStatusUseCase)
    factoryOf(::GetInstallEnvironmentUseCase)
    factoryOf(::ExecuteFlashOperationUseCase)
    factoryOf(::CheckFlashModuleMountUseCase)
    factoryOf(::GetManagerRuntimeInfoUseCase)
    factoryOf(::GetKernelFeatureSettingsUseCase)
    factoryOf(::SetSuEnabledUseCase)
    factoryOf(::SetKernelUmountEnabledUseCase)
    factoryOf(::SetShellSuAdbOnlyEnabledUseCase)
    factoryOf(::ConfigureSuLogUseCase)
    factoryOf(::SetSelinuxHideEnabledUseCase)
    factoryOf(::SetDefaultUmountModulesUseCase)
    factoryOf(::IsLateLoadModeUseCase)
    factoryOf(::GetAppProfileUseCase)
    factoryOf(::SetAppProfileUseCase)
    factoryOf(::GetAppSepolicyUseCase)
    factoryOf(::SetAppSepolicyUseCase)
    factoryOf(::ControlAppUseCase)
    factoryOf(::ValidateSepolicyUseCase)
    factoryOf(::GetDefaultUmountModulesUseCase)
    factoryOf(::GetSuSFSStatusUseCase)
    factoryOf(::SuSFSConfigUseCase)
    factoryOf(::ApplyLanguageUseCase)
    factoryOf(::IsSystemLanguageSettingsUseCase)
    factoryOf(::LaunchSystemLanguageSettingsUseCase)
    factoryOf(::GenerateBugreportUseCase)
    factoryOf(::ObserveStartupStateUseCase)
    factoryOf(::GetSuperUserAppGroupUseCase)
    factoryOf(::ObserveCatalogModulesUseCase)
    factoryOf(::ObserveModuleCatalogRefreshingUseCase)
    factoryOf(::RefreshModuleCatalogUseCase)
    factoryOf(::GetCatalogModuleUseCase)
    factoryOf(::IsCatalogModuleCurrentUseCase)
    factoryOf(::GetModuleReadmeUseCase)
    factoryOf(::GetModuleReleaseDetailsUseCase)
    factoryOf(::LoadModuleIconUseCase)
    factoryOf(::ResolveModuleUpdateUseCase)
    factoryOf(::GetModuleUpdateChangelogUseCase)
    factoryOf(::ObserveRepositorySourcesUseCase)
    factoryOf(::AddModuleRepositoryUseCase)
    factoryOf(::RenameModuleRepositoryUseCase)
    factoryOf(::RemoveModuleRepositoryUseCase)
    factoryOf(::ObserveProfileTemplatesUseCase)
    factoryOf(::ObserveProfileTemplateRefreshingUseCase)
    factoryOf(::ObserveProfileTemplateOfflineUseCase)
    factoryOf(::RefreshProfileTemplatesUseCase)
    factoryOf(::GetProfileTemplateUseCase)
    factoryOf(::SaveProfileTemplateUseCase)
    factoryOf(::DeleteProfileTemplateUseCase)
    factoryOf(::ImportProfileTemplatesUseCase)
    factoryOf(::ExportProfileTemplatesUseCase)
    factoryOf(::GetBooleanPreferenceUseCase)
    factoryOf(::SetBooleanPreferenceUseCase)
    factoryOf(::GetStringPreferenceUseCase)
    factoryOf(::SetStringPreferenceUseCase)
    factoryOf(::GetStringSetPreferenceUseCase)
    factoryOf(::SetStringSetPreferenceUseCase)
    factoryOf(::ObserveDynamicManagerStateUseCase)
    factoryOf(::RefreshDynamicManagerUseCase)
    factoryOf(::SelectDynamicManagerUseCase)
    factoryOf(::SetManualDynamicManagerUseCase)
    factoryOf(::ClearDynamicManagerUseCase)
    factoryOf(::ObserveSulogStateUseCase)
    factoryOf(::RefreshSulogUseCase)
    factoryOf(::EnableSulogUseCase)
    factoryOf(::CleanSulogUseCase)
    factoryOf(::ObserveUmountStateUseCase)
    factoryOf(::RefreshUmountPathsUseCase)
    factoryOf(::AddUmountPathUseCase)
    factoryOf(::RemoveUmountPathUseCase)
    factoryOf(::ObserveKernelFlashUseCase)
    factoryOf(::StartKernelFlashUseCase)
    factoryOf(::RemovePreferenceUseCase)
    factoryOf(::ObserveSuperUserStateUseCase)
    factoryOf(::RefreshSuperUsersUseCase)
    factoryOf(::BackupAllowlistUseCase)
    factoryOf(::ImportAllowlistUseCase)
    factoryOf(::FetchRemoteTextUseCase)
    factoryOf(::IsModuleUriAccessibleUseCase)
    factoryOf(::TakeModuleUriPermissionUseCase)
    factoryOf(::ExtractModuleNameUseCase)
    factoryOf(::ExtractModuleIdUseCase)
    factoryOf(::ObserveInstalledModulesUseCase)
    factoryOf(::RefreshInstalledModulesUseCase)
    factoryOf(::CalculateInstalledModuleSizeUseCase)
    factoryOf(::UpdateCachedModuleEnabledUseCase)
    factoryOf(::ExecuteModuleActionUseCase)
    factoryOf(::PeekOneshotScriptUseCase)
    factoryOf(::PrepareScriptUseCase)
    factoryOf(::ReleaseScriptUseCase)
    factoryOf(::RunScriptUseCase)
    factoryOf(::GetLastScriptRunUseCase)
    factoryOf(::SaveModuleActionLogUseCase)
    factoryOf(::SetModuleEnabledUseCase)
    factoryOf(::SetModuleRemovedUseCase)
    factoryOf(::TransliterateTextUseCase)
}

val viewModelModule = module {
    viewModel { parameters -> ScriptRunViewModel(parameters[0], get(), get(), get(), get(), get()) }
    viewModel { parameters ->
        AppProfileViewModel(
            uid = parameters[0],
            packageName = parameters[1],
            getAppGroup = get(),
            getProfile = get(),
            getDefaultUmountModules = get(),
            setProfile = get(),
            getSepolicy = get(),
            setSepolicy = get(),
            controlApp = get(),
            validateSepolicy = get(),
        )
    }
    viewModelOf(::HomeViewModel)
    viewModelOf(::InstallViewModel)
    viewModelOf(::MainIntentViewModel)
    viewModelOf(::KernelFlashViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::ModuleViewModel)
    viewModelOf(::SuperUserViewModel)
    viewModelOf(::SuSFSViewModel)
    viewModel { parameters -> ModuleRepoViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), parameters.getOrNull<String>()) }
    viewModel { parameters -> ModuleDetailViewModel(parameters[0], parameters[1], get(), get(), get(), get(), get(), get(), get()) }
    viewModelOf(::RepositorySourcesViewModel)
    viewModelOf(::TemplateViewModel)
    viewModel { parameters ->
        TemplateEditorViewModel(
            templateId = parameters[0],
            readOnly = parameters[1],
            isCreation = parameters[2],
            getTemplate = get(),
            saveTemplate = get(),
            deleteTemplate = get(),
        )
    }
    viewModelOf(::SulogViewModel)
    viewModelOf(::DynamicManagerViewModel)
    viewModelOf(::FlashViewModel)
    viewModelOf(::UmountManagerScreenViewModel)
    viewModel { parameters ->
        ExecuteModuleActionViewModel(
            moduleId = parameters[0],
            executeModuleAction = get(),
            saveModuleActionLog = get(),
        )
    }
}

val appModules = listOf(coreModule, repositoryModule, useCaseModule, viewModelModule)
