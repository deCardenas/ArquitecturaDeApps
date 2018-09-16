package com.albireo.arquitecturadeapps

import com.albireo.arquitecturadeapps.mvp.LoginMVP
import com.albireo.arquitecturadeapps.presenters.LoginPresenter
import com.albireo.arquitecturadeapps.service.data.User
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PresenterUnitTest {

    lateinit var presenter: LoginPresenter
    var user: User? = null

    @Mock
    lateinit var mockedModel: LoginMVP.Model
    @Mock
    lateinit var mockedView: LoginMVP.View

    @Before
    fun init() {
        //MockitoAnnotations.initMocks(this)
        user = User(0, "John", "Doe")
        //Mockito.`when`(mockedModel.getUser()).then { return@then user }
        //Mockito.`when`(mockedView.getFirstName()).then { return@then "John" }
        //Mockito.`when`(mockedView.getLastName()).then { return@then "Doe" }

        presenter = LoginPresenter(mockedModel)
        presenter.setView(mockedView)
    }

    @Test
    fun notExistsInteractionWithView() {
        presenter.getCurrentUser()
        Mockito.verify(mockedView, times(1)).showUserUnavailable()
    }

    @Test
    fun loadUserFromRepoValidUserIsPresent() {
        Mockito.`when`(mockedModel.getUser()).thenReturn(user)
        presenter.getCurrentUser()
        Mockito.verify(mockedModel, times(1)).getUser()
        Mockito.verify(mockedView, times(1)).setFirstName("John")
        Mockito.verify(mockedView, times(1)).setLastName("Doe")
        Mockito.verify(mockedView, never()).showUserUnavailable()
    }

    @Test
    fun showErrorMessageWhenUserIsUnavailable() {
        Mockito.`when`(mockedModel.getUser()).thenReturn(null)
        presenter.getCurrentUser()
        Mockito.verify(mockedModel, times(1)).getUser()
        Mockito.verify(mockedView, times(0)).setFirstName("John")
        Mockito.verify(mockedView, times(0)).setLastName("Doe")
        Mockito.verify(mockedView, times(1)).showUserUnavailable()
    }

    @Test
    fun buttonLoginClickedWhenFieldsAreOK() {
        Mockito.`when`(mockedView.getFirstName()).thenReturn("Diego Efrain")
        Mockito.`when`(mockedView.getLastName()).thenReturn("Cardenas")
        presenter.loginClicked()
        Mockito.verify(mockedView, times(1)).getFirstName()
        Mockito.verify(mockedView, times(1)).getLastName()
        Mockito.verify(mockedView, times(1)).showUserSaved()
        Mockito.verify(mockedModel, times(1)).createUser("Diego Efrain", "Cardenas")
    }

    @Test
    fun buttonLoginClickedWhenFirstNameIsEmpty() {
        //name is empty
        Mockito.`when`(mockedView.getFirstName()).thenReturn("")
        Mockito.`when`(mockedView.getLastName()).thenReturn("Cardenas")
        presenter.loginClicked()
        Mockito.verify(mockedView, times(1)).getFirstName()
        Mockito.verify(mockedView, times(1)).getLastName()
        Mockito.verify(mockedView, times(1)).showInputError()
    }

    @Test
    fun buttonLoginClickedWhenLastNameIsEmpty() {
        //last name is empty
        Mockito.`when`(mockedView.getFirstName()).thenReturn("Diego")
        Mockito.`when`(mockedView.getLastName()).thenReturn("")
        presenter.loginClicked()
        Mockito.verify(mockedView, times(1)).getFirstName()
        Mockito.verify(mockedView, times(1)).getLastName()
        Mockito.verify(mockedView, times(1)).showInputError()
    }

    @Test
    fun buttonLoginClickedWhenFieldsAreEmpty() {
        Mockito.`when`(mockedView.getFirstName()).thenReturn("")
        Mockito.`when`(mockedView.getLastName()).thenReturn("")
        presenter.loginClicked()
        Mockito.verify(mockedView, times(1)).getFirstName()
        Mockito.verify(mockedView, times(1)).getLastName()
        Mockito.verify(mockedView, times(1)).showInputError()
    }
}