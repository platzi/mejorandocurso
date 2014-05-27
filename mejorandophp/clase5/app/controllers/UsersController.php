<?php

use HireMe\Entities\User;
use HireMe\Managers\RegisterManager;
use HireMe\Repositories\CandidateRepo;

class UsersController extends BaseController {

    protected $candidateRepo;

    public function __construct(CandidateRepo $candidateRepo)
    {
        $this->candidateRepo = $candidateRepo;
    }

    public function signUp()
    {
        return View::make('users/sign-up');
    }

    public function register()
    {
        $user = $this->candidateRepo->newCandidate();
        $manager = new RegisterManager($user, Input::all());

        if ($manager->save())
        {
            return Redirect::route('home');
        }

        return Redirect::back()->withInput()->withErrors($manager->getErrors());
    }

} 