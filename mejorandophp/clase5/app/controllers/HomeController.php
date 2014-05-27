<?php

use HireMe\Repositories\CandidateRepo;

class HomeController extends BaseController {

    protected $candidateRepo;

    public function __construct(CandidateRepo $candidateRepo)
    {
        $this->candidateRepo = $candidateRepo;
    }

	public function index()
	{
        $latest_candidates = $this->candidateRepo->findLatest();

		return View::make('home', compact('latest_candidates'));
	}

}
