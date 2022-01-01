package cc.ptt.android.di

import cc.ptt.android.presentation.articlelist.ArticleListViewModel
import cc.ptt.android.presentation.articleread.ArticleReadViewModel
import cc.ptt.android.presentation.home.favoriteboards.FavoriteBoardsViewModel
import cc.ptt.android.presentation.home.hotarticle.HotArticleListViewModel
import cc.ptt.android.presentation.home.hotboard.HotBoardsViewModel
import cc.ptt.android.presentation.home.setting.SettingViewModel
import cc.ptt.android.presentation.login.LoginPageViewModel
import cc.ptt.android.presentation.searchboards.SearchBoardsModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { HotBoardsViewModel(get(), get(named("IO"))) }
    viewModel { ArticleListViewModel(get(), get(), get(named("IO"))) }
    viewModel { ArticleReadViewModel(get(), get(), get(named("IO")), get(), get()) }
    viewModel { LoginPageViewModel(get()) }
    viewModel { SearchBoardsModel(get(), get(named("IO"))) }
    viewModel { FavoriteBoardsViewModel(get(), get(named("IO")), get()) }
    viewModel { HotArticleListViewModel(get(), get(named("UI")), get(named("IO"))) }
    viewModel { SettingViewModel(get(), get()) }
}