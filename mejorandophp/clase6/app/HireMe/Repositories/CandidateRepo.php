<?php

namespace HireMe\Repositories;

use HireMe\Entities\Candidate;
use HireMe\Entities\Category;
use HireMe\Entities\User;

class CandidateRepo extends BaseRepo {

    public function getModel()
    {
        return new Candidate;
    }

    public function findLatest($take = 10)
    {
        return Category::with([
            'candidates' => function ($q) use ($take) {
                    $q->take($take);
                    $q->orderBy('created_at', 'DESC');
                },
            'candidates.user'
        ])->get();
    }

    public function newCandidate()
    {
        $user = new User();
        $user->type = 'candidate';
        return $user;
    }

} 